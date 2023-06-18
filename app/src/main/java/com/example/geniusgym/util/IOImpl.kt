package com.example.geniusgym.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * 需要 implementation 'com.google.code.gson:gson:2.9.0' 才可以用
 * 需要 implementation 'androidx.security:security-crypto:1.1.0-alpha05' 才可以用
 */

class IOImpl {

    /**
     * 存filedir 跟 cachedir 在宣告FILE()時只差兩句話
     * 將其包裝成可以複用的資料
     */
    class Mode{
        companion object{
            const val MODE_CACHE = 0x1
            const val MODE_MEMORY = 0x2
        }
    }

    /**
     * 所有class共用的方法
     */
    companion object{
        private fun getEncryptedFile(file: File, context: Context): EncryptedFile {
            val masterKeyAlias = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            return EncryptedFile.Builder(
                context,
                file, /* 加密檔案可以為內部/外部檔案，完全依照檔案路徑來決定 */
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()
        }

    }


    class Internal(private val context: Context) : IO {
        override fun saveFile(jsonObject: JsonObject, filename: String, mode : Int, encrypted : Boolean) {

            when (mode) {
                Mode.MODE_MEMORY -> {
                    if (encrypted){
                        val filerDir = File(context.filesDir, filename)
                        if (filerDir.exists()){
                            if (filerDir.delete()){
                                Log.d("IO_In_ME_FILE", "移除成功")
                            }else{
                                Log.d("IO_In_ME_FILE", "移除失敗")
                            }
                        }
                        getEncryptedFile(filerDir, context).openFileOutput()
                    }else{
                        context.deleteFile(filename)
                        context.openFileOutput(filename, Context.MODE_PRIVATE)
                    }
                        .bufferedWriter().use {
                            it.write(jsonObject.toString())
                        }
                }
                Mode.MODE_CACHE -> {
                    val cacheFile = File(context.cacheDir, filename)
                    if (cacheFile.exists()){
                        if (cacheFile.delete()){
                            Log.d("IO_In_CA_FILE", "移除成功")
                        }else{
                            Log.d("IO_In_CA_FILE", "移除失敗")
                        }
                    }
                    if (encrypted){
                        getEncryptedFile(cacheFile, context).openFileOutput()
                    }else{
                        FileOutputStream(cacheFile)
                    }
                        .bufferedWriter().use {
                        it.write(jsonObject.toString())
                    }
                }
                else -> {
                    throw java.lang.Exception("選取了錯誤的模式")
                }
            }
        }

        override fun saveFile(jsonArray: JsonArray, filename: String, mode : Int, encrypted : Boolean) {
            when (mode) {
                Mode.MODE_MEMORY -> {
                    if (encrypted){
                        val filerDir = File(context.filesDir, filename)
                        if (filerDir.exists()){
                            if (filerDir.delete()){
                                Log.d("IO_In_ME_AR_FILE", "移除成功")
                            }else{
                                Log.d("IO_In_ME_AR_FILE", "移除失敗")
                            }
                        }
                        getEncryptedFile(filerDir, context).openFileOutput()
                    }else{
                        context.deleteFile(filename)
                        context.openFileOutput(filename, Context.MODE_PRIVATE)
                    }
                        .bufferedWriter().use {
                            it.write(jsonArray.toString())
                        }
                }
                Mode.MODE_CACHE -> {
                    val cacheFile = File(context.cacheDir, filename)
                    if (cacheFile.exists()){
                        if (cacheFile.delete()){
                            Log.d("IO_In_CA_AR_FILE", "移除成功")
                        }else{
                            Log.d("IO_In_CA_AR_FILE", "移除失敗")
                        }
                    }
                    if (encrypted){
                        getEncryptedFile(cacheFile, context).openFileOutput()
                    }else{
                        FileOutputStream(cacheFile)
                    }
                        .bufferedWriter().use {
                            it.write(jsonArray.toString())
                        }
                }
                else -> {
                    throw java.lang.Exception("選取了錯誤的模式")
                }
            }
        }



        override fun loadObjectFile(filename: String, mode : Int, encrypted : Boolean): JsonObject? {
            val file : File =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.filesDir, filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.cacheDir, filename)
                    }
                    else -> {
                        throw java.lang.Exception("選取了錯誤的模式")
                    }
                }

            return if (file.exists()) {
                var jsonStr: String? = null
                if (encrypted){
                    getEncryptedFile(file, context).openFileInput()
                }else{
                    context.openFileInput("internal")
                }
                    .bufferedReader().useLines { lines ->
                        jsonStr = lines.fold("") { text, line -> "$text$line" }
                    }
                Gson().fromJson(jsonStr, JsonObject::class.java)
            } else {
                null
            }
        }

        override fun loadArrayFile(filename: String, mode : Int, encrypted : Boolean): JsonArray? {
            val file : File =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.filesDir, filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.cacheDir, filename)
                    }
                    else -> {
                        throw java.lang.Exception("選取了錯誤的模式")
                    }
                }

            return if (file.exists()) {
                var jsonStr: String? = null
                if (encrypted){
                    getEncryptedFile(file, context).openFileInput()
                }else{
                    context.openFileInput("internal")
                }
                    .bufferedReader().useLines { lines ->
                        jsonStr = lines.fold("") { text, line -> "$text$line" }
                    }
                Gson().fromJson(jsonStr, JsonArray::class.java)
            } else {
                null
            }
        }

    }

    class External(private val context: Context) : IO {

        override fun saveFile(jsonArray: JsonArray, filename: String, mode : Int, encrypted : Boolean) {
            if (!mediaMounted()){
                Toast.makeText(context, "尚未連接外部記憶體", Toast.LENGTH_SHORT).show()
                return
            }
            val file =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.getExternalFilesDir(null), filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.externalCacheDir, filename)
                    }
                    else -> {
                        throw FileNotFoundException("讀取資料錯誤")
                    }
                }
            if (file.exists()){
                if (file.delete()){
                    Log.d("IO_EX_FILE", "移除成功")
                }else{
                    Log.d("IO_EX_FILE", "移除成功")
                }
            }
            if (encrypted){
                getEncryptedFile(file, context).openFileOutput()
            }else{
                FileOutputStream(file)
            }
                .bufferedWriter().use {
                    it.write(jsonArray.toString())
            }
        }

        override fun saveFile(jsonObject: JsonObject, filename: String, mode : Int, encrypted : Boolean) {
            if (!mediaMounted()){
                Toast.makeText(context, "尚未連接外部記憶體", Toast.LENGTH_SHORT).show()
                return
            }
            val file =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.getExternalFilesDir(null), filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.externalCacheDir, filename)
                    }
                    else -> {
                        throw FileNotFoundException("讀取資料錯誤")
                    }
                }
            if (file.exists()){
                if (file.delete()){
                    Log.d("IO_EX_FILE", "移除成功")
                }else{
                    Log.d("IO_EX_FILE", "移除成功")
                }
            }
            if (encrypted){
                getEncryptedFile(file, context).openFileOutput()
            }else{
                FileOutputStream(file)
            }
                .bufferedWriter().use {
                    it.write(jsonObject.toString())
                }
        }

        override fun loadObjectFile(filename: String, mode : Int, encrypted : Boolean):JsonObject? {
            if (!mediaMounted()){
                Toast.makeText(context, "尚未連接外部記憶體", Toast.LENGTH_SHORT).show()
                return null
            }
            val file =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.getExternalFilesDir(null), filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.externalCacheDir, filename)
                    }
                    else -> {
                        throw FileNotFoundException("讀取資料錯誤")
                    }
                }
            if (file.exists()) {
                if (encrypted){
                    getEncryptedFile(file, context).openFileInput()
                }else{
                    FileInputStream(file)
                }
                    .bufferedReader().useLines { lines ->
                    val jsonStr = lines.fold("") { text, line -> "$text$line" }
                    return Gson().fromJson(jsonStr, JsonObject::class.java)
                }
            } else {
                return null
            }
        }

        override fun loadArrayFile(filename: String, mode : Int, encrypted : Boolean):JsonArray? {
            if (!mediaMounted()){
                Toast.makeText(context, "尚未連接外部記憶體", Toast.LENGTH_SHORT).show()
                return null
            }
            val file =
                when (mode) {
                    Mode.MODE_MEMORY -> {
                        File(context.getExternalFilesDir(null), filename)
                    }
                    Mode.MODE_CACHE -> {
                        File(context.externalCacheDir, filename)
                    }
                    else -> {
                        throw FileNotFoundException("讀取資料錯誤")
                    }
                }
            if (file.exists()) {
                if (encrypted){
                    getEncryptedFile(file, context).openFileInput()
                }else{
                    FileInputStream(file)
                }
                    .bufferedReader().useLines { lines ->
                        val jsonStr = lines.fold("") { text, line -> "$text$line" }
                        return Gson().fromJson(jsonStr, JsonArray::class.java)
                    }
            } else {
                return null
            }
        }

        private fun mediaMounted():Boolean{
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }
    }

    
    class SpIO(val context: Context) : SPIO {
        override fun saveApply(filename: String, key: String, value: String, encrypted : Boolean) {
            if (encrypted){
                getEncryptedPreferences(filename).edit()
                    .putString(key, value)
                    .apply()
            }else{
                context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit()
                    .putString(key, value)
                    .apply()
            }

        }

        override fun saveCommit(filename: String, key: String, value: String, encrypted : Boolean) {
            if (encrypted){
                getEncryptedPreferences(filename).edit()
                    .putString(key, value)
                    .commit()
            }else{
                context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit()
                    .putString(key, value)
                    .commit()
            }

        }

        override fun load(filename: String, key: String, encrypted : Boolean): String {
            return if (encrypted){
                val preferences = getEncryptedPreferences(filename)
                preferences.getString(key, "") ?: ""
            }else{
                context.getSharedPreferences(filename, Context.MODE_PRIVATE).getString(key, "")!!
            }

        }

        private fun getEncryptedPreferences(filename: String): SharedPreferences{
            val masterKeyAlias = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            return EncryptedSharedPreferences.create(
                context,
                // passing a file name to share a preferences
                filename,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

    }


}