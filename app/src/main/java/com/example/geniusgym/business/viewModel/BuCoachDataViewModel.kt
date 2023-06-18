package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.business.model.Member
import com.example.geniusgym.sharedata.MeShareData.javaWebUrl
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask

/**
 * 教練列表資料處理
 */
class BuCoachDataViewModel : ViewModel() {
    val url = javaWebUrl + "buCoach"

    // 原始教練列表
    private var BuCoachList = mutableListOf<Coach>()
    // 受監控的LiveData，一旦指派新值就會更新教練列表畫面
    val coaches: MutableLiveData<List<Coach>> by lazy { MutableLiveData<List<Coach>>() }
init {
    inti()
}
    fun inti(){
        val type = object : TypeToken<List<Coach>>() {}.type
        coaches.value = requestTask<List<Coach>>(url, respBodyType = type)
        println(coaches.value?.get(0))
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始教練列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            coaches.value = BuCoachList
        } else {
            val searchBucoachList = mutableListOf<Coach>()
            BuCoachList.forEach { bucoach ->
                if (bucoach.c_name!!.contains(newText, true)) {
                    searchBucoachList.add(bucoach)
                }
            }
            coaches.value = searchBucoachList
        }
    }

    /** 模擬取得遠端資料 */
//    private fun loadBuCoach() {
//        val BuCoachList = mutableListOf<testBuCoach>()
//        BuCoachList.add(testBuCoach("c01","內壢分店","a123","Tiv美美","女",null,"H212345678","桃園內壢","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","123@gmail.com","又閣是彼个 無代無誌想著的形影\n" +
//                "iū-koh sī hit ê, bô-tài-bô-tsì siūnn tio̍h ê hîng-iánn\n" +
//                "親像一陣風 吹落來 是春風少年\n" +
//                "tshin-tshiūnn tsi̍t tsūn-hong, tshue loh lâi, sī tshun hong siáu-liân\n" +
//                "\n" +
//                "坐嘛是思念 坐袂牢 真無聊的思念\n" +
//                "tsē mà-sī su-liām, tsē bē tiâu, tsin bô-liâu ê su-liām\n" +
//                "倒咧眠床頂 予月娘 笑規个暗暝\n" +
//                "tó leh bîn-tshn̂g tíng, hōo gue̍h-niû tshiò kui-ê àm-mî\n" +
//                " \n" +
//                "我行過你的世界\n" +
//                "guá kiânn-kuè lí ê sè-kài\n" +
//                "啥物我攏無愛\n" +
//                "siánn-mih guá lóng bô ài\n" +
//                "只想欲佇你心內（寫一條歌）\n" +
//                "tsí siūnn beh tī lí sim-lāi（siá tsi̍t tiâu kua）",R.drawable.seaotter2,true))
//        BuCoachList.add(testBuCoach("c02","桃園分店","a456","Tiv漂漂","女","0987654321","H287654321","桃園桃園","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","456@gmail.com","我是品嚐失敗的人啊\n" +
//                "沒有成為想要的模樣\n" +
//                "有著事與願違的遺憾\n" +
//                "包裝無能為力的悲傷\n" +
//                "我是被夢吞噬的人啊\n" +
//                "故作姿態卻還是徬徨\n" +
//                "享受掌聲也擁抱目光\n" +
//                "又有多少真讓人成長",R.drawable.seaotter2,true))
//        BuCoachList.add(testBuCoach("c03","中壢分店","a789","Tiv水水","女","0918273645","H218273645","桃園中壢","2023/5/31 14:30","2027/6/1 00:00","2023/5/31 14:30","b99","789@gmail.com",null,R.drawable.seaotter2,true))
//        this.BuCoachList = BuCoachList
//        this.coaches.value = this.BuCoachList
//    }


}