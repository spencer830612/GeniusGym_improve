package com.example.geniusgym.util

import android.provider.Settings.System.getString
import android.util.Log
import android.widget.Toast
import com.example.geniusgym.R
import com.example.geniusgym.sharedata.MeShareData.javaWebUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL
class WebRequestSpencer {

    private val CreditCardTag = "TAG_creditCardConnect"
    suspend fun httpGet(
        servlet: String,
    ): String {
        var jsonIn = ""
        val url = javaWebUrl + servlet
        withContext(Dispatchers.IO) {
                (URL(url).openConnection() as? HttpURLConnection)?.run {
                    if (responseCode == 200) {
                        inputStream.bufferedReader().useLines { lines ->
                            jsonIn = lines.fold("") { text, line -> "$text$line" }
                        }
                    }
                }
        }
        return jsonIn
    }

    suspend fun httpPost(
        servlet: String,
        jsonOut: String
    ): String {
        val url = javaWebUrl + servlet
        var jsonIn = ""
        withContext(Dispatchers.IO) {
            (URL(url).openConnection() as? HttpURLConnection)?.run {
                doInput = true
                doOutput = true
                setChunkedStreamingMode(0)
                useCaches = false
                requestMethod = "POST"
                setRequestProperty("content-type", "application/json")
                setRequestProperty("charset", "utf-8")

                outputStream.bufferedWriter().use {
                    it.write(jsonOut)
                }
                if (responseCode == 200) {
                    inputStream.bufferedReader().useLines { lines ->
                        jsonIn = lines.fold("") { text, line -> "$text$line" }
                    }
                }
            }
        }
        return jsonIn
    }

    suspend fun payResult(
        url: String,
        jsonOut: String,
        partnerKey: String
    ): String {
        var jsonIn = ""
        withContext(Dispatchers.IO) {
            (URL(url).openConnection() as? HttpURLConnection)?.run {
                Log.d(CreditCardTag, "openConnection()")
                doInput = true // allow inputs
                doOutput = true // allow outputs
                // 將送出請求內容分段傳輸，設定0代表使用預設大小
                setChunkedStreamingMode(0)
                useCaches = false
                // 一定要大寫
                requestMethod = "POST"

                // 加上content-type與x-api-key設定，否則錯誤
                // 參看 https://docs.tappaysdk.com/google-pay/zh/back.html#pay-by-prime-api
                setRequestProperty("content-type", "application/json")
                setRequestProperty("x-api-key", partnerKey)

                setRequestProperty("charset", "utf-8")
                outputStream.bufferedWriter().use { it.write(jsonOut) }
                if (responseCode == 200) {
                    inputStream.bufferedReader().useLines { lines ->
                        jsonIn = lines.fold("") { text, line -> "$text$line" }
                        Log.d(CreditCardTag, "input: $jsonIn")
                    }
                }
            }
        }
        return jsonIn
    }
}