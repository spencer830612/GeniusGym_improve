package com.example.geniusgym.member.controller

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeCoachInfoDetailBinding
import com.example.geniusgym.member.model.CoachBean
import com.example.geniusgym.member.viewmodel.MeCoachInfoDetailViewModel
import java.io.ByteArrayInputStream

class MeCoachInfoDetailFragment : Fragment() {

    private val viewModel: MeCoachInfoDetailViewModel by viewModels()
    private lateinit var binding: FragmentMeCoachInfoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeCoachInfoDetailBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.coachDetail = arguments?.getSerializable("CoachInfo", CoachBean::class.java) ?: CoachBean()
        }else{
            viewModel.coachDetail = arguments?.getSerializable("CoachInfo") as CoachBean
        }
        with(binding){
            tvMeCoachName.text = viewModel.coachDetail?.c_name
            tvMeCoachIntro.text = viewModel.coachDetail?.c_intro
            if (viewModel.coachDetail?.c_pic == null){
                ivMeCoachPic.setImageResource(R.drawable.a005)
            }else{
                val bis = ByteArrayInputStream(viewModel.coachDetail?.c_pic)
                val drawable = BitmapFactory.decodeStream(bis)
                ivMeCoachPic.setImageBitmap(drawable)
            }

        }

    }
}