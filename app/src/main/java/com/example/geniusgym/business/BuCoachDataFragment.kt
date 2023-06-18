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
import com.example.geniusgym.business.adapter.BuCoachDataAdapter
import com.example.geniusgym.business.adapter.BuMemberDataAdapter
import com.example.geniusgym.business.viewModel.BuCoachDataViewModel
import com.example.geniusgym.databinding.FragmentBuCoachDataBinding

class BuCoachDataFragment : Fragment() {
    private lateinit var binding: FragmentBuCoachDataBinding
    private lateinit var viewModel: BuCoachDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setTitle(R.string.btBuMenuCoachDataManage)
        binding = FragmentBuCoachDataBinding.inflate(inflater, container, false)
        val viewModel: BuCoachDataViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

           // viewModel?.inti()

            rvBuCoachData.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.coaches?.observe(viewLifecycleOwner) { coaches ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBuCoachData.adapter == null) {
                    rvBuCoachData.adapter = BuCoachDataAdapter(coaches)
                } else {
                    (rvBuCoachData.adapter as BuCoachDataAdapter).updateBuCoaches(coaches)
                }
            }


            fabBuCoachDataAdd.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_buCoachData_to_buCoachDataAdd)
            }
        }
    }


}