package com.example.geniusgym.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.R
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.databinding.FragmentSettingEdBinding
import com.example.geniusgym.member.MeActivity

class SettingEdFragment : Fragment() {

    private lateinit var binding: FragmentSettingEdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: SettingViewModel by viewModels()
        binding = FragmentSettingEdBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val preference = requireActivity().getPreferences(Context.MODE_PRIVATE)
            viewModel?.nickName?.value = preference.getString("sNickName","")
            viewModel?.Intro?.value = preference.getString("sIntro","")
            btSettingEdSend.setOnClickListener {
                requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                    .putString("sNickName",tieSeEdNickName.text.toString())
                    .putString("sIntro", tieSeEdSocialIntro.text.toString())
                    .apply()
                Navigation.findNavController(it).navigate(R.id.settingFragment)
            }
        }
    }


}