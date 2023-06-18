package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.databinding.FragmentMeBranchDetailBinding
import com.example.geniusgym.member.adapter.MeBranchAdapter
import com.example.geniusgym.member.viewmodel.MeBranchDetailViewModel

class MeBranchDetailFragment : Fragment() {

    private lateinit var binding: FragmentMeBranchDetailBinding
    private val viewModel: MeBranchDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBranchDetailBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            meRecycleBranch.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            meRecycleBranch.adapter = MeBranchAdapter(viewModel.storeBeans)
        }

    }
}