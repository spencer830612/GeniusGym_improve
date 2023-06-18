package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.databinding.FragmentMeTrainingMuscleBinding
import com.example.geniusgym.member.adapter.MeTrainingMuscleMainAdapter
import com.example.geniusgym.member.viewmodel.MeTrainingMuscleViewModel

class MeTrainingMuscleFragment : Fragment() {
    private lateinit var binding: FragmentMeTrainingMuscleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MeTrainingMuscleViewModel by viewModels()
        binding = FragmentMeTrainingMuscleBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvMeTrainingMuscle.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvMeTrainingMuscle.adapter == null) {
                    rvMeTrainingMuscle.adapter = MeTrainingMuscleMainAdapter(items)
                } else {
                    (rvMeTrainingMuscle.adapter as MeTrainingMuscleMainAdapter).update(items)
                }
            }
        }
    }
}