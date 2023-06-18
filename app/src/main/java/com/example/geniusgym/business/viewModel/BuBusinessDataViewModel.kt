package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Business
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.business.model.testBuBusiness
import com.example.geniusgym.business.model.testBuMember
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask

/**
 * 員工列表資料處理
 */
class BuBusinessDataViewModel : ViewModel() {
    val url = MeShareData.javaWebUrl + "buBuz"
   // val url = "http://10.0.2.2:8080/geninusgym_bg/buBuz"
    // 原始員工列表
    private var BuBusinessList = mutableListOf<Business>()
    // 受監控的LiveData，一旦指派新值就會更新員工列表畫面
    val bubuzz: MutableLiveData<List<Business>> by lazy { MutableLiveData<List<Business>>() }

    fun inti(){
        val type = object : TypeToken<List<Business>>() {}.type
        bubuzz.value = requestTask<List<Business>>(url, respBodyType = type)
        println(bubuzz.value?.get(0))
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始員工列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            bubuzz.value = BuBusinessList
        } else {
            val searchBubuzList = mutableListOf<Business>()
            BuBusinessList.forEach { bubuz ->
                if (bubuz.b_name!!.contains(newText, true)) {
                    searchBubuzList.add(bubuz)
                }
            }
            bubuzz.value = searchBubuzList
        }
    }

    /** 模擬取得遠端資料 */
//    private fun loadBuBusiness() {
//        val BuBusinessList = mutableListOf<Business>()
//        BuBusinessList.add(testBuBusiness("b01","內壢分店","a123","Tiv水水","女","0912345678","H212345678","桃園內壢","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","123@gmail.com",R.drawable.seaotter2,true))
//        BuBusinessList.add(testBuBusiness("b02","中壢分店","a456","Tiv美美","女","0987654321","H287654321","桃園中壢","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","456@gmail.com",R.drawable.seaotter2,true))
//        BuBusinessList.add(testBuBusiness("b03","桃園分店","a789","Tiv漂漂","女","0918273645","H218273645","桃園桃園","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","789@gmail.com",R.drawable.seaotter2,true))
//        this.BuBusinessList = BuBusinessList
//        this.bubuzz.value = this.BuBusinessList
//    }


}