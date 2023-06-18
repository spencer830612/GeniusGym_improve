package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAnoxBigViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberRecordAnoxBinding


class CoCalenderMemberRecordAnoxFragment : Fragment() {
    private lateinit var binding: FragmentCoCalenderMemberRecordAnoxBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : CoCalenderMemberRecordAnoxBigViewModel by viewModels()
        binding =  FragmentCoCalenderMemberRecordAnoxBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvCoCaMeReAnox.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner){items ->
                if(rvCoCaMeReAnox.adapter == null){
                    rvCoCaMeReAnox.adapter = CoCaMeReAnBigAdapter(items)
                }else{
                    (rvCoCaMeReAnox.adapter as CoCaMeReAnBigAdapter).update(items)
                }
            }
        }
    }
}
