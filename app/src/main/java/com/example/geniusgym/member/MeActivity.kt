package com.example.geniusgym.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.geniusgym.R
import com.example.geniusgym.databinding.ActivityMeBinding

class MeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeBinding
    private lateinit var navController: NavController
    private lateinit var destinationMap : Map<Int, MotionLayout>
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//      因為直接用findnavicontroller會發生找不到還未建立的fragment，因此要先找到fragment再把他附值給navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
//        將底部導覽的頁面用map的key、value存儲
        setSupportActionBar(binding.toolbarMebr)

       supportActionBar?.apply {
           setDisplayHomeAsUpEnabled(true)
           setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_24) // 如果您有自定義的返回圖標，可以使用此方法設置
        }

        destinationMap = mapOf(
            R.id.meHomeFragment to binding.includedHome.homeMontionLayout,
            R.id.meBranchFragment to binding.includedBranch.branchMontionLayout,
            R.id.socailNavFragment to binding.includedSocial.socialMontionLayout,
            R.id.notificationFragment to binding.includedNotification.notificationMontionLayout,
            R.id.meMemberFragment to binding.includedMember.memberMontionLayout,
            R.id.settingFragment to binding.includedSetting.settingMontionLayout

        )
        appBarConfiguration = AppBarConfiguration(destinationMap.keys)
//      設定這個方法自動更新ActionBar的title
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

//      使用遍例的方法將每個map中的layout(也就是底部導覽的元件)設置點擊時，會跳轉到哪一個頁面
        destinationMap.forEach{map ->
            map.value.setOnClickListener {
                navController.navigate(map.key)
            }
        }
//      設定跳轉時不會返回，會直接退出app，並且設定動畫相關的東西
        navController.addOnDestinationChangedListener{controller, destination, argument ->

            controller.popBackStack()
            destinationMap.values.forEach{it.progress = 0f}
            destinationMap[destination.id]?.transitionToEnd()

        }

//        onBackPressedDispatcher.addCallback(this, OnBackPresseClick)



    }

//    private val OnBackPresseClick = object : OnBackPressedCallback(true){
//        override fun handleOnBackPressed() {
//            val alertDialog = AlertDialog.Builder(this@MeActivity)
//            alertDialog.setTitle("確定要離開嗎")
//
//            alertDialog.setPositiveButton("確定"){dialog, which ->
//                dialog.dismiss()
//                finish()
//            }
//            alertDialog.setNegativeButton("取消"){dialog, which ->
//                dialog.dismiss()
//            }
//            alertDialog.show()
//        }
//
//    }




}


