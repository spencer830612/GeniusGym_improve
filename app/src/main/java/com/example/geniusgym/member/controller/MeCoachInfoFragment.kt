package com.example.geniusgym.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.geniusgym.databinding.FragmentMeCoachInfoBinding
import com.example.geniusgym.member.MeActivity
import com.example.geniusgym.member.adapter.MeCoachInfoAdapter
import com.example.geniusgym.member.viewmodel.MeCoachInfoViewModel

class MeCoachInfoFragment : Fragment() {

    private val viewModel: MeCoachInfoViewModel by viewModels()
    private lateinit var binding: FragmentMeCoachInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeCoachInfoBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            recycMeCoachinfo.adapter = MeCoachInfoAdapter(viewModel.coachinfos)
            recycMeCoachinfo.layoutManager = GridLayoutManager(requireContext(), 2)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}