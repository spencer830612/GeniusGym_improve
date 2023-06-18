package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.geniusgym.CoCalenderMemberRecordAllFragment
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberRecordBinding
import com.google.android.material.tabs.TabLayoutMediator

class CoCalenderMemberRecordFragment : Fragment() {

    private lateinit var binding: FragmentCoCalenderMemberRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewModel: CoCalenderMemberRecordViewModel by viewModels()
        binding = FragmentCoCalenderMemberRecordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        handleViews()
        return binding.root
    }


    private fun handleViews() {
        val pages = listOf(
            Page(
                getString(R.string.pageCoCalenderMemberRecordOx),
                CoCalenderMemberRecordOxFragment()
            ), Page(
                getString(R.string.pageCoCalenderMemberRecordAnox),
                CoCalenderMemberRecordAnoxFragment()
            ), Page(
                getString(R.string.pageCoCalenderMemberRecordAll),
                CoCalenderMemberRecordAllFragment()
            )
        )

        with(binding) {
            vpCoCaMeRe.adapter = MyFragmentStateAdapter(this@CoCalenderMemberRecordFragment, pages)
          //  tlCoCaMeRe.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"))
            TabLayoutMediator(tlCoCaMeRe, vpCoCaMeRe) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
               // tab.view.setBackgroundColor(pages[position].color)
            }.attach()

        }
    }

    class Page(var title: String,
              // var color: Int,
               var fragment: Fragment)

    class MyFragmentStateAdapter(activity: CoCalenderMemberRecordFragment, private var pages: List<Page>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].fragment
        }

    }
}