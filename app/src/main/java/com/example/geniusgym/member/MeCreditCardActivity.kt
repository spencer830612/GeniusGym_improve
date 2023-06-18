package com.example.geniusgym.member

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.geniusgym.R
import com.example.geniusgym.databinding.ActivityMeCreditCardBinding
import com.example.geniusgym.member.model.MeBuyPointBean
import com.example.geniusgym.member.model.MePointBean
import com.example.geniusgym.util.WebRequestSpencer
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData
import com.google.android.gms.wallet.TransactionInfo
import com.google.android.gms.wallet.WalletConstants
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import tech.cherri.tpdirect.api.*

class MeCreditCardActivity : AppCompatActivity() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private val requestCode = 101
    private lateinit var binding: ActivityMeCreditCardBinding
    private lateinit var tpdGooglePay: TPDGooglePay
    private lateinit var btGooglePay: RelativeLayout
    private lateinit var item : MeBuyPointBean
    // 測試環境網址
    private val sandbox = "https://sandbox.tappaysdk.com/"

    // 取得Prime後跟TapPay確定支付的連線網址
    private val primeUrl = "tpc/payment/pay-by-prime"

    // 信用卡種類
    private val cardTypes = arrayOf(
        TPDCard.CardType.Visa,
        TPDCard.CardType.MasterCard,
        TPDCard.CardType.JCB,
        TPDCard.CardType.AmericanExpress
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeCreditCardBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//        val bundle = this.intent.extras
//        val getItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle?.getSerializable("saveItem", MeBuyPointBean::class.java)
//        } else {
//            bundle?.getSerializable("saveItem") as MeBuyPointBean
//        }
//        if (getItem != null) {
//            item = getItem
//        }

        Log.d(myTag, "SDK version is " + TPDSetup.getVersion())
        TPDSetup.initInstance(
            this,
            getString(R.string.TapPay_AppID).toInt(),
            getString(R.string.TapPay_AppKey),
            TPDServerType.Sandbox
        )
        // include元件必須使用findViewById()取得，而不能使用binding物件取得
        btGooglePay = findViewById(R.id.btGooglePay)
        /* 先將Google Pay按鈕設定為不可點擊，
           之後執行prepareGooglePay()檢查可使用Google Pay時再改成可點擊 */
        btGooglePay.isEnabled = false
        prepareGooglePay()
        // 先將「確認付款」按鈕設定為不可點擊，
        // 之後onActivityResult()取得本機支付資訊後再改成可點擊
        binding.btConfirm.isEnabled = false



    }

    private fun prepareGooglePay() {
        // 建立商店物件
        val tpdMerchant = TPDMerchant()
        // 設定商店名稱
        tpdMerchant.merchantName = getString(R.string.TapPay_MerchantName)
        // 設定允許的信用卡種類
        tpdMerchant.supportedNetworks = cardTypes
        // 建立消費者物件，並設定需要填寫項目
        val tpdConsumer = TPDConsumer()
        // 不需要電話號碼
        tpdConsumer.isPhoneNumberRequired = false
        // 不需要運送地址
        tpdConsumer.isShippingAddressRequired = false
        // 不需要Email
        tpdConsumer.isEmailRequired = false
        tpdGooglePay = TPDGooglePay(this, tpdMerchant, tpdConsumer)
        // 檢查使用者裝置是否支援Google Pay
        tpdGooglePay.isGooglePayAvailable { isReadyToPay, message ->
            Log.d(myTag, "Pay with Google availability: ${isReadyToPay}; message: $message")
            if (isReadyToPay) {
                // 可使用Google Pay，將Google Pay按鈕改成可點擊並加上監聽器
                btGooglePay.isEnabled = true
                btGooglePay.setOnClickListener {
                    // 跳出user資訊視窗讓user確認，確認後會呼叫onActivityResult()
                    tpdGooglePay.requestPayment(
                        TransactionInfo.newBuilder()
                            .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                            .setTotalPrice("10") // 消費總金額
                            .setCurrencyCode("TWD") // 設定幣別
                            .build(), requestCode
                    )
                }
            } else {
                btGooglePay.isEnabled = false
                val text = "${getString(R.string.textCannotUseGPay)}: $message"
                binding.tvResult.text = text
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(myTag, "onActivityResult()")
        if (requestCode == this.requestCode) {
            with(binding) {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        data?.let {
                            // 取得本機支付資訊(使用者同意支付的款項與信用卡資訊)
                            PaymentData.getFromIntent(data)?.let { paymentData ->
                                showCardInfo(paymentData)
                                // 已經取得本機支付資訊，將「確認付款」按鈕改成可點擊並加上監聽器
                                // 點擊後就會將支付資訊送至TapPay並取得prime
                                btConfirm.isEnabled = true
                                btConfirm.setOnClickListener {
                                    getPrimeFromTapPay(paymentData)
                                }
                            }
                        }
                    }
                    Activity.RESULT_CANCELED -> {
                        btConfirm.isEnabled = false
                        btConfirm.setOnClickListener(null)
                        tvResult.setText(R.string.textCanceled)
                    }
                    AutoResolveHelper.RESULT_ERROR -> {
                        btConfirm.isEnabled = false
                        AutoResolveHelper.getStatusFromIntent(data)?.let { status ->
                            val text = "status code: " + status.statusCode +
                                    " , message: " + status.statusMessage
                            Log.d(myTag, text)
                            tvResult.text = text
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * 只取得支付資訊當中的信用卡資訊並顯示
     */
    private fun showCardInfo(paymentData: PaymentData) {
        val gson = Gson()
        val paymentDataJO = gson.fromJson(paymentData.toJson(), JsonObject::class.java)
        val cardDescription =
            paymentDataJO["paymentMethodData"].asJsonObject["description"].asString
        val text = "請確認信用卡資訊: $cardDescription"
        binding.tvCardInfo.text = text
    }

    /**
     * 將本機支付資訊送至TapPay並取得prime
     *
     * @param paymentData 本機支付資訊
     */
    private fun getPrimeFromTapPay(paymentData: PaymentData) {
        /* 呼叫getPrime()只將支付資料提交給TapPay以取得prime (代替卡片的一次性字串，此字串的時效為 30 秒)，
            參看https://docs.tappaysdk.com/google-pay/zh/reference.html#prime */
        /* 一般而言，手機提交支付、信用卡資料給TapPay後，TapPay會將信用卡等資訊送至Bank確認是否合法，
               Bank會回一個暫時編號給TapPay方便後續支付確認，TapPay儲存該編號後再回一個自編prime給手機，
               手機再傳給app server，app server再呼叫payByPrime方法提交給TapPay，以確認這筆訂單，
               此時app server就可發訊息告訴手機用戶訂單成立。
               參看圖示 https://docs.tappaysdk.com/google-pay/zh/home.html#home 向下捲動即可看到 */
        tpdGooglePay.getPrime(
            paymentData,
            { prime, _, _ ->
                /* 手機得到prime後，一般會傳給商家app server端儲存在DB後再傳給TapPay，以確認這筆訂單。
                   現在為了方便，手機直接提交給TapPay。
                   得到的結果為JSON，其中"msg":"Success"代表支付成功 */
                val resultJson = generatePayByPrimeForSandBox(
                    prime,
                    getString(R.string.TapPay_PartnerKey),
                    getString(R.string.TapPay_MerchantID)
                )
                val jsonObject = Gson().fromJson(resultJson, JsonObject::class.java)
                if (jsonObject["msg"].asString == "Success") {
                    Toast.makeText(
                        this,
                        R.string.textPaymentSuccess,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val text = "支付結束，TapPay回應的結果訊息:\n$resultJson"
                Log.d(myTag, text)
                binding.tvResult.text = text
            }
        ) { status: Int, reportMsg: String ->
            val text =
                "TapPay getPrime failed. status: $status, message: $reportMsg"
            Log.d(myTag, text)
            binding.tvResult.text = text
        }
    }

    // 將交易資訊送至TapPay測試區
    private fun generatePayByPrimeForSandBox(
        prime: String,
        partnerKey: String,
        merchantId: String
    ): String {
        val paymentJO = JsonObject()
        paymentJO.addProperty("partner_key", partnerKey)
        paymentJO.addProperty("prime", prime)
        paymentJO.addProperty("merchant_id", merchantId)
        paymentJO.addProperty("amount", 250)
        paymentJO.addProperty("currency", "TWD")
        paymentJO.addProperty("order_number", "SN0001")
        paymentJO.addProperty("details", "500點")
        val cardHolderJO = JsonObject()
        cardHolderJO.addProperty("name", "Ron")
        cardHolderJO.addProperty("phone_number", "+886912345678")
        cardHolderJO.addProperty("email", "ron@email.com")
        paymentJO.add("cardholder", cardHolderJO)

        // TapPay測試區網址
        val url = sandbox + primeUrl
        var result: String
        // 請求屬性要加上content-type與x-api-key設定，否則錯誤
        runBlocking {
            result = WebRequestSpencer().payResult(url, paymentJO.toString(), partnerKey)
        }
        return result
    }

}