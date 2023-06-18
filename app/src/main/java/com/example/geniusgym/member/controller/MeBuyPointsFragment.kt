package com.example.geniusgym.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeBuyPointsBinding
import com.example.geniusgym.databinding.RecycleMeBuyPointsBinding
import com.example.geniusgym.member.MePointsViewModel
import com.example.geniusgym.member.adapter.MeBuyPointsAdapter
import com.example.geniusgym.member.viewmodel.MeBuyPointsViewModel

class MeBuyPointsFragment : Fragment() {
    private lateinit var binding: RecycleMeBuyPointsBinding
    private lateinit var adapter: MeBuyPointsAdapter
    private lateinit var viewModel: MeBuyPointsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MeBuyPointsViewModel::class.java]
        binding = RecycleMeBuyPointsBinding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MeBuyPointsAdapter(binding.viewmodel!!.buypoints)
        binding.btRecyclerlist.layoutManager = LinearLayoutManager(requireContext())
        binding.btRecyclerlist.adapter = adapter
    }

}