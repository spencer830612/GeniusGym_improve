package com.example.geniusgym.business

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geniusgym.R
import com.example.geniusgym.business.viewModel.BuChooseBranchViewModel

class BuChooseBranchFragment : Fragment() {

    companion object {
        fun newInstance() = BuChooseBranchFragment()
    }

    private lateinit var viewModel: BuChooseBranchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bu_choose_branch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuChooseBranchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}