package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Class_Info
import java.text.SimpleDateFormat
import java.util.*

class BuClassDataAddViewModel : ViewModel() {
    val classs: MutableLiveData<Class_Info> by lazy { MutableLiveData<Class_Info>(Class_Info()) }

    fun bhToString():String? {
        if (classs.value?.bh_id == 1){
            return "緯育店"
        }
        if (classs.value?.bh_id == 2){
            return "台北店"
        }
        if (classs.value?.bh_id == 3){
            return "桃園店"
        }
        if (classs.value?.bh_id == 4){
            return "新竹店"
        }
        if (classs.value?.bh_id == 5){
            return "南京復興店"
        }
        return null
    }

    fun scToString():String? {
        if (classs.value?.sc_id == 2){
            return "靜態"
        }
        if (classs.value?.sc_id == 3){
            return "心肺訓練"
        }
        if (classs.value?.sc_id == 4){
            return "跑步"
        }
        if (classs.value?.sc_id == 5){
            return "槓鈴肩推"
        }
        if (classs.value?.sc_id == 6){
            return "啞鈴肩推"
        }
        if (classs.value?.sc_id == 7){
            return "啞鈴側平舉"
        }
        if (classs.value?.sc_id == 8){
            return "啞鈴前平舉"
        }
        if (classs.value?.sc_id == 9){
            return "站姿肩推"
        }
        if (classs.value?.sc_id == 10){
            return "啞鈴握推"
        }
        if (classs.value?.sc_id == 11){
            return "槓鈴握推"
        }
        if (classs.value?.sc_id == 12){
            return "蝴蝶機夾胸"
        }
        if (classs.value?.sc_id == 13){
            return "繩索下斜夾胸"
        }
        if (classs.value?.sc_id == 14){
            return "槓鈴斜上推"
        }
        if (classs.value?.sc_id == 15){
            return "飛輪"
        }
        return null
    }

    fun startTimeToString():String? {
      val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      return classs.value?.ci_start_time?.let { format.format(it) }
    }
    fun endTimeToString():String? {
      val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      return classs.value?.ci_ed_time?.let { format.format(it) }
    }
    fun regiTimeToString():String? {
      val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      return classs.value?.ci_regi_time?.let { format.format(it) }
    }
    fun regiEdTimeToString():String? {
      val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      return classs.value?.ci_regi_ed_time?.let { format.format(it) }
    }

}