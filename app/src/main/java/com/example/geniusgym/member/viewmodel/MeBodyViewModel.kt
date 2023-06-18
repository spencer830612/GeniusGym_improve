package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.MeBodyBean

class MeBodyViewModel : ViewModel() {
    val text :MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val bodydetail : MutableLiveData<MeBodyBean> by lazy { MutableLiveData<MeBodyBean>(
        MeBodyBean()
    )}


init{
    showmebody()
}

    private fun showmebody() {
        val fakeBodyDetail = MeBodyBean(
            bdhight = "180cm",
            bdkg = "70kg",
            bdfat = "23%",
        )

        bodydetail.value = fakeBodyDetail
    }
}



