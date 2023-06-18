package com.example.geniusgym.setting

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    val sClassNoEnable : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sNotifiedEnable : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sNewClassEnable : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sClassScheNoti : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sWorkScheNoti : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sPrivacyShowEnable : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sSocialFollowEnabled : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sSocialAllowInfoAccess : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val sSocialAllowFansCountsAccess : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val nickName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val Intro : MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun load(preference: SharedPreferences) {
        sClassNoEnable?.value = preference.getBoolean("sClassNoEnable",false)
        sNotifiedEnable?.value = preference.getBoolean("sNotifiedEnable",false)
        sNewClassEnable?.value = preference.getBoolean("sNewClassEnable",false)
        sClassScheNoti?.value = preference.getBoolean("sClassScheNoti",false)
        sWorkScheNoti?.value = preference.getBoolean("sWorkScheNoti",false)
        sPrivacyShowEnable?.value = preference.getBoolean("sPrivacyShowEnable",false)
        sSocialFollowEnabled?.value = preference.getBoolean("sSocialFollowEnabled",false)
        sSocialAllowInfoAccess?.value = preference.getBoolean("sSocialAllowInfoAccess",false)
        sSocialAllowFansCountsAccess?.value = preference.getBoolean("sSocialAllowFansCountsAccess",false)
        nickName?.value = preference.getString("sNickName","")
        Intro?.value = preference.getString("sIntro","")
    }
}