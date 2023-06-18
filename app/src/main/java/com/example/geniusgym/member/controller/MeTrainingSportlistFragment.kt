package com.example.geniusgym.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geniusgym.R
import com.example.geniusgym.member.viewmodel.MeTrainingSportlistViewModel

class MeTrainingSportlistFragment : Fragment() {

    companion object {
        fun newInstance() = MeTrainingSportlistFragment()
    }

    private lateinit var viewModel: MeTrainingSportlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_me_training_sportlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeTrainingSportlistViewModel::class.java)
        // TODO: Use the ViewModel
    }

}