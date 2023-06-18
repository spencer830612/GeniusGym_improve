package com.example.geniusgym.business

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.business.adapter.BuBusinessDataAdapter
import com.example.geniusgym.business.adapter.BuMemberDataAdapter
import com.example.geniusgym.business.viewModel.BuBusinessDataViewModel
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataBinding
import java.util.*

class BuBusinessDataFragment : Fragment() {
    private lateinit var binding: FragmentBuBusinessDataBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setTitle(R.string.btBuMenuBusinessDataManage)
        binding = FragmentBuBusinessDataBinding.inflate(inflater, container, false)
        val viewModel: BuBusinessDataViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

            viewModel?.inti()

            rvBuBusinessData.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.bubuzz?.observe(viewLifecycleOwner) { bubuzz ->
                // adapter為null要建立新的adapter；之後只要呼叫updateBuBusiness(bubuzz)即可
                if (rvBuBusinessData.adapter == null) {
                    rvBuBusinessData.adapter = BuBusinessDataAdapter(bubuzz)
                } else {
                    (rvBuBusinessData.adapter as BuBusinessDataAdapter).updateBuBusiness(bubuzz)
                }
            }


            fabBuBusinessDataAdd.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_buBusinessData_to_buBusinessDataAdd)
            }
        }
    }



}