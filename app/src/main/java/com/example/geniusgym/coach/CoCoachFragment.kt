package com.example.geniusgym.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geniusgym.databinding.FragmentCoCoachBinding

class CoCoachFragment : Fragment() {
    private lateinit var binding:FragmentCoCoachBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoCoachBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coActivity = requireActivity() as CoActivity
        val coach = coActivity.binding.viewModel?.coach?.value
        with(binding){
            tvCoCoMeNumberShow.text = coach?.c_id
            tvCoCoMeStartShow.text = coach?.c_start_date
            tvCoCoPhoneNumberShow.text = coach?.c_cell
            tvCoCoUsernameShow.text = coach?.c_name
        }
    }


}