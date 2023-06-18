package com.example.geniusgym.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject

interface IO {
    /**
    file can be cacheDir or fileDir
     please use file : File = context.getDir(dirName. Context.Mode_Private)
     */

    /**
     * @param jsonObject 放自己需要的存檔的資料
     * @param filename 資料夾名稱
     * @param mode 選擇使用IOImpl.Mode的兩種其中一種，如果不是會跳Exception
     * @param encrypted 是否加密
     */
    fun saveFile(jsonObject: JsonObject, filename: String, mode : Int, encrypted : Boolean)

    /**
     * @param jsonArray 放自己需要的存檔的資料
     * @param filename 資料夾名稱
     * @param mode 選擇使用IOImpl.Mode的兩種其中一種，如果不是會跳Exception
     * @param encrypted 是否加密
     */
    fun saveFile(jsonArray: JsonArray, filename: String, mode : Int, encrypted : Boolean)

    /**
     * @param filename 要讀取的檔案名稱
     * @param mode 要選取的模式
     * @param encrypted 是否加密
     */
    fun loadObjectFile(filename: String, mode : Int, encrypted : Boolean):JsonObject?

    /**
     * @param filename 要讀取的檔案名稱
     * @param mode 要選取的模式
     * @param encrypted 是否加密
     */
     fun loadArrayFile(filename: String, mode : Int, encrypted : Boolean):JsonArray?
}

interface SPIO{
    /**
     *apply會異步將資料存進sharepreference
     */
    fun saveApply(filename: String, key: String ,value : String, encrypted : Boolean)
    /**
     *使用commit方式是使用ui執行續存儲資料，有時極端情況需要使用，大部分使用apply即可
     */
    fun saveCommit(filename: String, key: String ,value : String, encrypted : Boolean)
    fun load(filename: String, key: String, encrypted : Boolean) : String
}