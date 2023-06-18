package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.geniusgym.databinding.FragmentMeBodyBinding
import com.example.geniusgym.member.viewmodel.MeBodyViewModel

class MeBodyFragment : Fragment() {
    private lateinit var binding : FragmentMeBodyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val viewModel: MeBodyViewModel by viewModels()
        binding = FragmentMeBodyBinding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}