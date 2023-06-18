package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.ClassInfo

class MeCheckoutViewModel : ViewModel() {
    var buylist : ArrayList<ClassInfo> = ArrayList()
}