package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.StoreBean

class MeBranchDetailViewModel : ViewModel() {
    private val _storeBeans : MutableList<StoreBean> by lazy { mutableListOf() }
    val storeBeans : List<StoreBean> = _storeBeans
    fun updateData(){
        _storeBeans.add(StoreBean())
    }
}