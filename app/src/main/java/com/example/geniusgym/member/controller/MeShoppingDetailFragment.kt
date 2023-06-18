package com.example.geniusgym.member.controller

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeShoppingDetailBinding
import com.example.geniusgym.member.model.ClassInfo
import com.example.geniusgym.member.viewmodel.MeShoppingDetailViewModel

class MeShoppingDetailFragment : Fragment() {

    private lateinit var binding : FragmentMeShoppingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeShoppingDetailBinding.inflate(LayoutInflater.from(requireContext()))
        val viewModel = ViewModelProvider(this)[MeShoppingDetailViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable("lesson", ClassInfo::class.java)
                    ?.let { it1 -> binding.viewmodel?.update(it1) }
            }else{
                binding.viewmodel?.update(it.getSerializable("lesson") as ClassInfo )
            }
        }
        binding.viewmodel?.setTextToCell(binding)

    }


}