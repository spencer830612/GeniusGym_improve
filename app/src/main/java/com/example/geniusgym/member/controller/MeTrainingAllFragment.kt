package com.example.geniusgym.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geniusgym.R
import com.example.geniusgym.member.viewmodel.MeTrainingAllViewModel

class MeTrainingAllFragment : Fragment() {

    companion object {
        fun newInstance() = MeTrainingAllFragment()
    }

    private lateinit var viewModel: MeTrainingAllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_me_training_all, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeTrainingAllViewModel::class.java)
        // TODO: Use the ViewModel
    }

}