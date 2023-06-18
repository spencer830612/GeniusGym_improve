package com.example.geniusgym.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geniusgym.R
import com.example.geniusgym.business.viewModel.BuPostManageViewModel
import com.example.geniusgym.databinding.FragmentBuPostManageBinding

class BuPostManageFragment : Fragment() {
    private lateinit var binding: FragmentBuPostManageBinding
    private lateinit var viewModel: BuPostManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setTitle(R.string.btBuMenuPostManage)
        binding = FragmentBuPostManageBinding.inflate(inflater, container, false)
        return binding.root
    }


}