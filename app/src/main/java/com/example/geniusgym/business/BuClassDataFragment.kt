package com.example.geniusgym.business

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
import com.example.geniusgym.business.adapter.BuClassDataAdapter
import com.example.geniusgym.business.viewModel.BuBusinessDataViewModel
import com.example.geniusgym.business.viewModel.BuClassDataViewModel
import com.example.geniusgym.databinding.FragmentBuClassDataBinding

class BuClassDataFragment : Fragment() {
    private lateinit var binding: FragmentBuClassDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setTitle(R.string.btBuMenuClassDataManage)
        binding = FragmentBuClassDataBinding.inflate(inflater, container, false)
        val viewModel: BuClassDataViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

            viewModel?.inti()

            rvBuClassData.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.classes?.observe(viewLifecycleOwner) { classes ->
                // adapter為null要建立新的adapter；之後只要呼叫updateBuClass(classes)即可
                if (rvBuClassData.adapter == null) {
                    rvBuClassData.adapter = BuClassDataAdapter(classes)
                } else {
                    (rvBuClassData.adapter as BuClassDataAdapter).updateBuClass(classes)
                }
            }

            fabBuClassDataAdd.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_buClassData_to_buClassDataAdd)
            }
        }
    }


}