package tw.idv.william.androidwebserver.core.service

import com.example.geniusgym.business.model.Member
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Type
import java.net.CookieHandler
import java.net.CookieManager
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import java.time.LocalDateTime

//data class Member(
//    var id: Int,
//    var username: String,
//    var password: String,
//    var nickname: String,
//    var pass: Boolean,
////    var lastUpdateDate: LocalDateTime,
//    var successful: Boolean
//)

fun main() {
    val url = "http://localhost:8080/geniusgym_bg/"
    val type = object : TypeToken<List<Member>>(){}.type
    val list = requestTask<List<Member>>(url, respBodyType = type)
    println(list)
}

inline fun <reified T> requestTask(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? = runBlocking {
    val deferred: Deferred<T?> = coroutineScope {
        async(Dispatchers.IO) {
            if (CookieHandler.getDefault() == null) {
                CookieHandler.setDefault(CookieManager())
            }
            request<T>(url, method, reqBody, respBodyType)
        }
    }
    deferred.await()
}

inline fun <reified T> request(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? {
    var conn: HttpURLConnection? = null
    var result: T? = null
    try {
        conn = URL(url).openConnection() as HttpURLConnection
        with(conn) {
            requestMethod = method
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            useCaches = false
            doOutput = reqBody != null
            reqBody?.run {
                outputStream.use {
                    val writer = outputStream.writer()
                    writer.write(GSON.toJson(reqBody))
                    writer.flush()
                }
            }
            if (responseCode == HTTP_OK) {
                inputStream.use {
                    result = GSON.fromJson<T>(inputStream.reader(), respBodyType)
                }
            }
        }
    } finally {
        conn?.disconnect()
    }
    return result
}

val GSON: Gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd HH:mm:ss")
    .create()