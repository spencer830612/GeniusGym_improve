package com.example.geniusgym.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.geniusgym.coach.calendarMemberList.model.ClassItem
import com.example.geniusgym.member.model.MetrainBean
import com.example.geniusgym.databinding.FragmentMePersonalTrainBinding
import java.io.Serializable
import java.time.LocalDate

class MePersonalTrainViewModel : ViewModel() {
    val classname: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val classtime: MutableLiveData<LocalDate> by lazy { MutableLiveData<LocalDate>() }

    private var metrainlist = listOf<MetrainBean>()
    val metrainitem: MutableLiveData<List<MetrainBean>> by lazy { MutableLiveData<List<MetrainBean>>() }
    init{
        loadItems()
    }
    fun search(input: String?) {
        val searchList = if (input == null || input.isEmpty()) {
            metrainlist
        } else {
            metrainlist.filter { item ->
                searchItem(item, input.trim())
            }
        }
        metrainitem.value = searchList

    }

    private fun searchItem(item: MetrainBean, searchText: String): Boolean {
        return item.mtdate.contains(searchText, ignoreCase = false)

    }
    private fun loadItems(){

        val metrainlist = mutableListOf<MetrainBean>()
        metrainlist.add(
            MetrainBean(
                mtid="111",
                mtstarttime = "12:00",
                mtendtime="14:00",
                mtdate="2023-06-12",
                myclassname="螺璇有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="112",
                mtstarttime = "15:00",
                mtendtime="16:00",
                mtdate="2023-06-12",
                myclassname="突刺有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="113",
                mtstarttime ="15:00",
                mtendtime="16:00",
                mtdate="2023-06-13",
                myclassname="倒立有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="114",
                mtstarttime ="13:00",
                mtendtime="14:00",
                mtdate="2023-06-14",
                myclassname="螺璇有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="115",
                mtstarttime ="16:00",
                mtendtime="17:00",
                mtdate="2023-06-14",
                myclassname="螺璇有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="116",
                mtstarttime ="19:00",
                mtendtime="20:00",
                mtdate="2023-06-14",
                myclassname="螺璇有氧"))

        metrainlist.add(
            MetrainBean(
                mtid="117",
                mtstarttime ="10:00",
                mtendtime="11:00",
                mtdate="2023-06-15",
                myclassname="一對一課程"))

        this.metrainlist = metrainlist
        this.metrainitem.value = this.metrainlist
    }

}