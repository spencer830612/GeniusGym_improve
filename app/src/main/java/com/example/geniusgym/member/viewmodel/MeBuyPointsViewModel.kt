package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.MeBuyPointBean
import com.example.geniusgym.member.model.MePointBean

class MeBuyPointsViewModel : ViewModel() {
    val buypoints : MutableList<MeBuyPointBean> = mutableListOf()

   init {
       loadItem()
   }

   private fun loadItem(){
      buypoints.add(
         MeBuyPointBean(
            bpamount = "300",
            bpmoney = "NT 150"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "400",
            bpmoney = "NT 200"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "500",
            bpmoney = "NT 250"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "600",
            bpmoney = "NT 300"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "700",
            bpmoney = "NT 350"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "800",
            bpmoney = "NT 400"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "900",
            bpmoney = "NT 450"))

      buypoints.add(
         MeBuyPointBean(
            bpamount = "1000",
            bpmoney = "NT 500"))

   }
}