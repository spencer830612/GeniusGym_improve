package com.example.geniusgym.coach

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportRecordBigItem
import com.example.geniusgym.databinding.ActivityCoBinding
import com.google.gson.GsonBuilder
import java.net.ConnectException

class CoActivity : AppCompatActivity() {

    public lateinit var binding: ActivityCoBinding
    private lateinit var navigateController: NavController
    public var memberSportRecord = mutableListOf<SportRecordBigItem>()
    public var memberSportBigRecord: SportRecordBigItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CoViewModel by viewModels()
        binding = ActivityCoBinding.inflate(LayoutInflater.from(this))
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        try {
            this.binding.viewModel?.loadSportFromPreference(this)
            this.binding.viewModel?.loadSportSmallItem()
            this.binding.viewModel?.loadSportBigItem()
        } catch (e: ConnectException) {
            e.printStackTrace()
            Toast.makeText(this,"連線失敗",Toast.LENGTH_SHORT)
        }
        navigateController = findNavController(R.id.fragmentCoContainerView)
        with(binding) {
            includeHome.homeMontionLayout.progress = 1f
            tvCoActivityHead.text = "首頁"
            includeHome.homeMontionLayout.setOnClickListener {
                if (includeHome.homeMontionLayout.progress == 0f) {
                    MotionToZero()
                    includeHome.homeMontionLayout.transitionToEnd()
                }
                tvCoActivityHead.text = "首頁"
                llCoActivityHead.visibility = View.VISIBLE
                navigateController.navigate(R.id.coHomeFragment)
            }
            includeCalendar.coachMotionLayout.setOnClickListener {
                tvCoActivityHead.text = "行事曆"
                llCoActivityHead.visibility = View.VISIBLE
                if (includeCalendar.coachMotionLayout.progress == 0f) {
                    MotionToZero()
                    includeCalendar.coachMotionLayout.transitionToEnd()
                }
                navigateController.navigate(R.id.coCalendarClassFragment)
            }
            includeSocial.socialMontionLayout.setOnClickListener {
                if (includeSocial.socialMontionLayout.progress == 0f) {
                    MotionToZero()
                    includeSocial.socialMontionLayout.transitionToEnd()
                }
                llCoActivityHead.visibility = View.GONE
                navigateController.navigate(R.id.socialNavFragment)
            }
            includeNotification.notificationMontionLayout.setOnClickListener {
                if (includeNotification.notificationMontionLayout.progress == 0f) {
                    MotionToZero()
                    includeNotification.notificationMontionLayout.transitionToEnd()
                }
                tvCoActivityHead.text = "通知"
                llCoActivityHead.visibility = View.VISIBLE
                navigateController.navigate(R.id.notificationFragment)
            }
            includeInformation.memberMontionLayout.setOnClickListener {
                if (includeInformation.memberMontionLayout.progress == 0f) {
                    MotionToZero()
                    includeInformation.memberMontionLayout.transitionToEnd()
                }
                tvCoActivityHead.text = "資訊"
                llCoActivityHead.visibility = View.VISIBLE
                navigateController.navigate(R.id.coCoachFragment)
            }
        }
    }

    private fun MotionToZero() {
        with(binding) {
            includeHome.homeMontionLayout.progress = 0f
            includeCalendar.coachMotionLayout.progress = 0f
            includeSocial.socialMontionLayout.progress = 0f
            includeNotification.notificationMontionLayout.progress = 0f
            includeInformation.memberMontionLayout.progress = 0f
        }
    }

    override fun onPause() {
        super.onPause()
        val gson = GsonBuilder().create()
        val sportSmallItemsJson = gson.toJson(this.binding.viewModel?.sportSmallItems?.value)
        val sportBigItems = gson.toJson(this.binding.viewModel?.sportBigItems?.value)
        this.getPreferences(Context.MODE_PRIVATE).edit()
            .putString("sportSmallItems", sportSmallItemsJson)
            .putString("sportBigItems", sportBigItems)
            .apply()
        println("Write prefernece successful!")
    }

    fun networkErrorToast() {
        Toast.makeText(this, "網路連線錯誤", Toast.LENGTH_SHORT)
    }
}