package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.CoachBean

class MeCoachInfoViewModel : ViewModel() {


    private val _coachinfos: MutableList<CoachBean> by lazy { mutableListOf() }
    val coachinfos : List<CoachBean> = _coachinfos

//    fun update(){
//        _coachinfos.add(CoachBean())
//    }

    init {
        update()
    }
    fun update(){

        _coachinfos.add(CoachBean("桃園 hawk", null, 1, "證照：\n" +
                "IPTFA國際康體專業證照\n" +
                "中華民國龍舟協會丙級教練\n" +
                "中華民國龍舟協會丙級裁判\n" +
                "中華民國體適能C級\n" +
                "PCW徒手街頭健身、肌內效\n" +
                "\n" +
                "經歷：\n" +
                "中正運動中心 家教課程/樂齡課程\n" +
                "北投、松山運動中心團課教練\n" +
                "企業包班指定 團課教練\n" +
                "2018台北國際龍舟季軍\n" +
                "2018碧潭龍舟公開賽男子組冠軍\n" +
                "2018海峽兩岸運動會雙冠\n" +
                "2019台北國際龍舟賽男子組冠軍\n" +
                "2020亞洲龍舟錦標賽混合組國手資格\n" +
                "2021高雄城市龍舟錦標賽小型混合組冠軍\n" +
                "2022高雄城市龍舟錦標賽小型男子組冠軍\n" +
                "\n" +
                "專長：\n" +
                "個人體能鍛練\n" +
                "體態評估\n" +
                "功能性訓練\n" +
                "龍舟\n" +
                "彈立帶/藥球/平衡碟/瑜珈球應用\n" +
                "\n" +
                "興趣：\n" +
                "吃飯\n" +
                "睡覺\n" +
                "訓練"))
        _coachinfos.add(CoachBean("王曉明", null, 1, "大家好，我是博博，我的專業是教大家如何用螺旋突刺，在家裡可以把所有東西刺的不要不要的"))


    }
}