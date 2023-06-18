package com.example.geniusgym.coach.calendarMemberList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.calendarMemberList.model.ClassItem
import com.example.geniusgym.coach.calendarMemberList.model.MemberItem


class CoCalendarMemberListViewModel : ViewModel() {
    private var itemList = listOf<MemberItem>()
    val items: MutableLiveData<List<MemberItem>> by lazy { MutableLiveData<List<MemberItem>>() }
    val item: MutableLiveData<ClassItem> by lazy { MutableLiveData<ClassItem>() }
    init {
        loadItems()
    }

    fun search(input: String?) {
        val searchList = if (input == null || input.isEmpty()) {
            itemList
        } else {
            itemList.filter { item ->
                searchItem(item, input.trim())
            }
        }
        items.value = searchList
    }

    private fun searchItem(item: MemberItem, searchText: String): Boolean {
        return item.classId.contains(searchText, ignoreCase = false)
    }

    private fun loadItems() {
        val itemList = mutableListOf<MemberItem>()
        // memberID 不可以是 dfg
        itemList.add(
            MemberItem(
                classId = "1234",
                memberId = "R05221017",
                name = "美秀集團",
            )
        )
        itemList.add(
            MemberItem(
                classId = "1234",
                memberId = "R05221018",
                name = "原子邦妮",
            )
        )
        itemList.add(
            MemberItem(
                classId = "1234",
                memberId = "R05221019",
                name = "宋德鶴",
            )
        )
        itemList.add(
            MemberItem(
                classId = "1234",
                memberId = "R05221020",
                name = "胡凱兒",
            )
        )
        itemList.add(
            MemberItem(
                classId = "12311111",
                memberId = "R05221021",
                name = "康士坦的變化球",
            )
        )
        itemList.add(
            MemberItem(
                classId = "12311111",
                memberId = "R05221022",
                name = "FORMOZA",
            )
        )

        itemList.add(
            MemberItem(
                classId = "1231112",
                memberId = "R05221023",
                name = "芒果醬B",
            )
        )
        itemList.add(
            MemberItem(
                classId = "1231112",
                memberId = "R05221024",
                name = "Gummy B",
            )
        )
        itemList.add(
            MemberItem(
                classId = "12311111",
                memberId = "R05221025",
                name = "步行者",
            )
        )
        itemList.add(
            MemberItem(
                classId = "12311111",
                memberId = "R05221026",
                name = "老王樂隊",
            )
        )
        itemList.add(
            MemberItem(
                classId = "456",
                memberId = "R05221027",
                name = "草東沒有派對",
            )
        )
        itemList.add(
            MemberItem(
                classId = "456",
                memberId = "R05221028",
                name = "血肉果汁機",
            )
        )
        itemList.add(
            MemberItem(
                classId = "789",
                memberId = "R05221029",
                name = "甜約翰",
            )
        )
        itemList.add(
            MemberItem(
                classId = "789",
                memberId = "R05221030",
                name = "逃走鮑伯",
            )
        )
        itemList.add(
            MemberItem(
                classId = "101112",
                memberId = "R05221031",
                name = "胭脂虎",
            )
        )
        itemList.add(
            MemberItem(
                classId = "101112",
                memberId = "R05221032",
                name = "賀爾蒙少年",
            )
        )
        itemList.add(
            MemberItem(
                classId = "99819923",
                memberId = "M81023",
                name = "Danny"
            )
        )
        this.itemList = itemList
        this.items.value = this.itemList
    }
}
