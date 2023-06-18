package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.geniusgym.databinding.FragmentMeTrainingRecordBinding
import com.example.geniusgym.member.MeActivity
import com.example.geniusgym.member.model.WorkoutSmallItem
import com.example.geniusgym.member.viewmodel.MeTrainingRecordViewModel

class MeTrainingRecordFragment : Fragment() {
    private lateinit var binding: FragmentMeTrainingRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeTrainingRecordBinding.inflate(inflater, container, false)
        val viewModel: MeTrainingRecordViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val MeActivity = requireActivity() as MeActivity
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("item")?.let {
                    viewModel?.sc_id = (it as WorkoutSmallItem).sc_id
                    viewModel?.sportName?.value = it.sc_name
                }
            }
            val member = MeActivity
        }
    }

}