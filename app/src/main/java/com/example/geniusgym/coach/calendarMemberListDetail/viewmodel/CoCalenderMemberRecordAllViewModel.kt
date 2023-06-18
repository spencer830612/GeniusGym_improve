package com.example.geniusgym.coach.calendarMemberListDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem

class CoCalenderMemberRecordAllcardViewModel : ViewModel(){
    val item: MutableLiveData<SportSmallItem> by lazy { MutableLiveData<SportSmallItem>() }
}

class CoCalenderMemberRecordAllViewModel : ViewModel() {
    val items: MutableLiveData<List<SportSmallItem>> by lazy { MutableLiveData<List<SportSmallItem>>() }
    private var itemList = mutableListOf<SportSmallItem>()



    fun search(searchText: String?) {
        val searchList =
            if (searchText == null || searchText.isBlank()) {
                itemList
            } else {
                itemList.filter { item ->
                    searchItem(item, searchText.trim())
                }
            }
        items.value = searchList
    }

    private fun searchItem(item: SportSmallItem, searchText: String): Boolean {
        return item.sc_name.contains(searchText, true)
    }

    fun load(sportSmallItem: List<SportSmallItem>?) {
        /*val list = mutableListOf<SportSmallItem>()

        list.add(SportSmallItem("1", "1", "槓鈴肩推"))
        list.add(SportSmallItem("1", "2", "啞鈴肩推"))
        list.add(SportSmallItem("1", "3", "啞鈴側平舉"))
        list.add(SportSmallItem("1", "4", "啞鈴前平舉"))
        list.add(SportSmallItem("1", "5", "站姿肩推"))
        list.add(SportSmallItem("2", "1", "啞鈴握推"))
        list.add(SportSmallItem("2", "2", "槓鈴握推"))
        list.add(SportSmallItem("2", "3", "蝴蝶機夾胸"))
        list.add(SportSmallItem("2", "4", "繩索下斜夾胸"))
        list.add(SportSmallItem("2", "5", "槓鈴斜上推"))
        list.add(SportSmallItem("1", "1", "飛輪"))
        list.add(SportSmallItem("1", "2", "靜態"))
        list.add(SportSmallItem("1", "3", "心肺訓練"))
        list.add(SportSmallItem("1", "4", "跑步"))
        list.add(SportSmallItem("1", "5", "舞蹈"))*/
        sportSmallItem?.let {
            this.itemList = it as MutableList<SportSmallItem>
            this.items.value = this.itemList
        }
    }
}