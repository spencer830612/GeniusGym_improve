package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.databinding.FragmentMeTrainingAerobicBinding
import com.example.geniusgym.member.adapter.MeTrainingAerobicAdapter
import com.example.geniusgym.member.viewmodel.MeTrainingAerobicViewModel

class MeTrainingAerobicFragment : Fragment() {
    private lateinit var binding: FragmentMeTrainingAerobicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MeTrainingAerobicViewModel by viewModels()
        binding = FragmentMeTrainingAerobicBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvMeTraingAerobic.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvMeTraingAerobic.adapter == null) {
                    rvMeTraingAerobic.adapter = MeTrainingAerobicAdapter(items)
                } else {
                    (rvMeTraingAerobic as MeTrainingAerobicAdapter).update(items)
                }

            }
        }
    }

}