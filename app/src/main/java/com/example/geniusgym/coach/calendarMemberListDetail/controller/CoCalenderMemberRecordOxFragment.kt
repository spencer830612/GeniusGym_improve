package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordOxViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberRecordOxBinding


class CoCalenderMemberRecordOxFragment : Fragment() {
    private lateinit var binding: FragmentCoCalenderMemberRecordOxBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewmodel: CoCalenderMemberRecordOxViewModel by viewModels()
        binding = FragmentCoCalenderMemberRecordOxBinding.inflate(inflater, container, false)
        binding.viewModel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val coActivity = requireActivity() as CoActivity
            viewModel?.load(coActivity)
            rvCoCaMeReOx.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvCoCaMeReOx.adapter == null) {
                    rvCoCaMeReOx.adapter = CoCaMeReOxAdapter(items)
                } else {
                    (rvCoCaMeReOx.adapter as CoCaMeReOxAdapter).update(items)
                }
            }
        }
    }
}