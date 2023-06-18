package com.example.geniusgym.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geniusgym.databinding.FragmentMeTrainingMuscleSmallBinding
import com.example.geniusgym.member.viewmodel.MeTrainingMuscleSmallViewModel

class MeTrainingMuscleSmallFragment: Fragment() {
    private lateinit var binding: FragmentMeTrainingMuscleSmallBinding
    private lateinit var id:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewModel: MeTrainingMuscleSmallViewModel by viewModels()
        binding = FragmentMeTrainingMuscleSmallBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("id")?.let {
                    id = it as String
                }
            }
//            rvMeTrainingMuscleSmall.layoutManager = LinearLayoutManager(requireContext())
//            val meActivity = requireActivity() as MeActivity
//            val workoutSmallItem = meActivity.binding.viewModel?.workoutSmallItem?.value
//            viewModel?.load(workoutSmallItem, id)
//            viewModel?.items?.observe(viewLifecycleOwner) { items ->
//                if (rvMeTrainingMuscleSmall.adapter == null) {
//                    rvMeTrainingMuscleSmall.adapter = MeTrainingMuscleSmallAdapter(items)
//                } else {
//                    (rvMeTrainingMuscleSmall.adapter as MeTrainingMuscleSmallAdapter).update(items)
//                }
//            }
        }
    }
}