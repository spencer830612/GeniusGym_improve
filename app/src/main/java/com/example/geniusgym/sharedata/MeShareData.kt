package com.example.geniusgym.sharedata

object MeShareData {
    //    分店名稱(傳遞資料用)
    var branchName: String = ""

    //    預設個人點數剩餘
    var personPoint: Int = 10000

    //    DirectMap
    const val IntervalMillsOnMap = 10000L
    const val MinUpdateDistanceMeters = 1000f
    const val MapZoom = 50f

    //    QR Code TimeCountDown
    const val CountDownPerSecond = 1000L
    const val CountDownTotalSecond = 300
    const val LocalDateTimeInitHours = 0
    const val LocalDateTimeInitMinutes = 5
    const val LocalDateTimeInitSeconds = 0
    const val LocalDateTimeToTextFormat = "mm:ss"

    //  url
    const val javaWebUrl = "http://192.168.67.1:8080/geninusgym_bg/"
    //const val javaWebUrl = "http://10.0.2.2:8080/geninusgym_bg/"
}