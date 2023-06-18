package com.example.geniusgym.coach.calendarMemberList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberList.model.ClassItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.util.WebRequestSpencer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class CoCalendarClassViewModel : ViewModel() {
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val Date: MutableLiveData<LocalDate> by lazy { MutableLiveData<LocalDate>() }

    private var itemList = listOf<ClassItem>()
    val items: MutableLiveData<List<ClassItem>> by lazy { MutableLiveData<List<ClassItem>>() }

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

    private fun searchItem(item: ClassItem, searchText: String): Boolean {
        return item.ci_date.contains(searchText, ignoreCase = false)
    }

    suspend fun loadFromSQL(coActivity: CoActivity) {
        val gson = GsonBuilder().create()
        val c_id = coActivity.binding.viewModel?.coach?.value?.c_id.toString()
        val type = object : TypeToken<List<ClassItem>>() {}.type
        val type1 = object : TypeToken<List<SportSmallItem>?>() {}.type
        val json: String = WebRequestSpencer().httpGet("ClassInfo/$c_id")
        val classItem = gson.fromJson<List<ClassItem>>(json, type)
        this.itemList = classItem
        this.items.value = this.itemList
    }

    private fun loadItems() {
        val itemList = mutableListOf<ClassItem>()
        itemList.add(
            ClassItem(
                ci_id = "1234",
                ci_start_time = "11:00",
                ci_ed_time = "12:00",
                ci_date = "2023-06-12",
                ci_name = "螺璇有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "1231112",
                ci_start_time = "13:00",
                ci_ed_time = "14:00",
                ci_date = "2023-06-12",
                ci_name = "突刺有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "12311111",
                ci_start_time = "13:00",
                ci_ed_time = "14:00",
                ci_date = "2023-06-13",
                ci_name = "倒立有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "456",
                ci_start_time = "15:00",
                ci_ed_time = "16:00",
                ci_date = "2023-06-13",
                ci_name = "螺璇有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "789",
                ci_start_time = "13:00",
                ci_ed_time = "14:00",
                ci_date = "2023-06-14",
                ci_name = "螺璇有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "101112",
                ci_start_time = "15:00",
                ci_ed_time = "16:00",
                ci_date = "2023-06-14",
                ci_name = "螺璇有氧"
            )
        )
        itemList.add(
            ClassItem(
                ci_id = "99819923",
                ci_start_time = "10:00",
                ci_ed_time = "11:00",
                ci_date = "2023-06-15",
                ci_name = "一對一課程"
            )
        )
        this.itemList = itemList
        this.items.value = this.itemList
    }
}