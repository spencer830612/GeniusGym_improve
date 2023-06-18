package com.example.geniusgym.coach

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberList.model.MemberItem
import com.example.geniusgym.coach.calendarMemberList.viewmodel.CoCalendarMemberDetailViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberDetailBinding

class CoCalendarMemberDetailFragment : Fragment() {

    private lateinit var binding:FragmentCoCalendarMemberDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : CoCalendarMemberDetailViewModel by viewModels()
        binding = FragmentCoCalendarMemberDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val coActivity = requireActivity() as CoActivity
        with(binding){
            tvCoCaMeDetail.setBackgroundResource(R.color.teal_700)
            tvCoCaMeDetail.setOnClickListener{
                Navigation.findNavController(requireActivity(), R.id.fcvCoCaMe).navigate(R.id.coCalenderMemberStaticFragment)
                tvCoCaMeDetail.setBackgroundResource(R.color.teal_700)
                tvCoCaMeRecord.setBackgroundColor(Color.parseColor("#1C1B1F"))
            }
            tvCoCaMeRecord.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.fcvCoCaMe).navigate(R.id.coCalenderMemberRecordFragment)
                tvCoCaMeRecord.setBackgroundResource(R.color.teal_700)
                tvCoCaMeDetail.setBackgroundColor(Color.parseColor("#1C1B1F"))
            }
        }
    }
}