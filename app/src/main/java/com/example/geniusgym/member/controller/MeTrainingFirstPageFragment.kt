package com.example.geniusgym.member.controller

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.geniusgym.databinding.FragmentMeTrainingFirstPageBinding
import com.example.geniusgym.member.viewmodel.MeTrainingFirstPageViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MeTrainingFirstPageFragment : Fragment() {

    private lateinit var binding: FragmentMeTrainingFirstPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MeTrainingFirstPageViewModel by viewModels()
        binding = FragmentMeTrainingFirstPageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        tabViews()
        return binding.root
    }

    private fun tabViews() {
        val workoutTabs = listOf(
            workoutTab(
                "有氧",
                MeTrainingAerobicFragment()
            ), workoutTab(
                "肌力",
                MeTrainingMuscleFragment()
            ), workoutTab(
                "全部",
                MeTrainingAllFragment()
            )
        )
        with(binding) {
            vpMeTrainFirstPage.adapter =
                MyFPTFragmentStateAdapter(this@MeTrainingFirstPageFragment, workoutTabs)
            tlMeTrainFirstPage.setSelectedTabIndicatorColor(Color.parseColor("#3DB7FF"))
            TabLayoutMediator(tlMeTrainFirstPage, vpMeTrainFirstPage) { tab, position ->
                tab.text = workoutTabs[position].title
            }.attach()
        }
    }

    class workoutTab(val title: String, val fragment: Fragment)

    class MyFPTFragmentStateAdapter(
        activity: MeTrainingFirstPageFragment,
        private var workoutTabs: List<workoutTab>
    ) :
        FragmentStateAdapter(activity) {

        override fun getItemCount(): Int {
            return workoutTabs.size
        }

        override fun createFragment(position: Int): Fragment {
            return workoutTabs[position].fragment
        }
    }
}