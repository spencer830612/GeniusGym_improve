package com.example.geniusgym

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberListDetail.controller.CoCaMeReAllAdapter
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAllViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberRecordAllBinding

class CoCalenderMemberRecordAllFragment : Fragment() {
    private lateinit var binding: FragmentCoCalenderMemberRecordAllBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CoCalenderMemberRecordAllViewModel by viewModels()
        binding = FragmentCoCalenderMemberRecordAllBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val coActivity = requireActivity() as CoActivity
            val sportSmallItem = coActivity.binding.viewModel?.sportSmallItems?.value
            viewModel?.load(sportSmallItem)
            svCoCaMeReAll.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }

            })
            rvCoCaMeReAll.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvCoCaMeReAll.adapter == null) {
                    rvCoCaMeReAll.adapter = CoCaMeReAllAdapter(items)
                } else {
                    (rvCoCaMeReAll.adapter as CoCaMeReAllAdapter).update(items)
                }
            }
        }
    }
}

