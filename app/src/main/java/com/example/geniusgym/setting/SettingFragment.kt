package com.example.geniusgym.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.R
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.databinding.FragmentSettingBinding
import com.example.geniusgym.member.MeActivity

class SettingFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewModel: SettingViewModel by activityViewModels()
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity() as MeActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preference = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(binding) {
            viewModel?.load(preference)
            tvNotifiedEnable.setOnClickListener(this@SettingFragment)
            tvClassNoEnable.setOnClickListener(this@SettingFragment)
            tvNewClassEnable.setOnClickListener(this@SettingFragment)
            tvClassScheNoti.setOnClickListener(this@SettingFragment)
            tvWorkScheNoti.setOnClickListener(this@SettingFragment)
            tvPrivacyShowEnable.setOnClickListener(this@SettingFragment)
            ivSocialIntro.setOnClickListener(this@SettingFragment)
            tvSocialFollowEnabled.setOnClickListener(this@SettingFragment)
            tvSocialAllowInfoAccess.setOnClickListener(this@SettingFragment)
            tvSocialAllowFansCountsAccess.setOnClickListener(this@SettingFragment)
            /*etSocialNickName.setOnEditorActionListener { _, actionId, _ ->
                if (finish(actionId)) {
                    etSocialNickName.isEnabled = false
                    true
                } else {
                    false // 返回 false 表示未處理該事件
                }
            }*/
/*            etSocialIntro.setOnEditorActionListener { _, actionId, _ ->
                if (finish(actionId)) {
                    etSocialNickName.isEnabled = false
                    true
                } else {
                    false // 返回 false 表示未處理該事件
                }
            }*/

        }
    }

/*    private fun finish(actionId: Int): Boolean {
        return actionId == EditorInfo.IME_ACTION_NEXT ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_NULL
    }*/

    override fun onClick(v: View?) {

        with(binding) {

            when (v?.id) {
                R.id.tvClassNoEnable -> sClassNoEnable.isChecked = !sClassNoEnable.isChecked
                R.id.tvNotifiedEnable -> sNotifiedEnable.isChecked = !sNotifiedEnable.isChecked
                R.id.tvNewClassEnable -> sNewClassEnable.isChecked = !sNewClassEnable.isChecked
                R.id.tvClassScheNoti -> sClassScheNoti.isChecked = !sClassScheNoti.isChecked
                R.id.tvWorkScheNoti -> sWorkScheNoti.isChecked = !sWorkScheNoti.isChecked
                R.id.tvPrivacyShowEnable -> sPrivacyShowEnable.isChecked =
                    !sPrivacyShowEnable.isChecked
                R.id.tvSocialFollowEnabled -> sSocialFollowEnabled.isChecked =
                    !sSocialFollowEnabled.isChecked
                R.id.tvSocialAllowInfoAccess -> sSocialAllowInfoAccess.isChecked =
                    !sSocialAllowInfoAccess.isChecked
                R.id.tvSocialAllowFansCountsAccess -> sSocialAllowFansCountsAccess.isChecked =
                    !sSocialAllowFansCountsAccess.isChecked
                R.id.ivSocialIntro -> {
                    Navigation.findNavController(ivSocialIntro).navigate(R.id.settingEdFragment)
                }
                else -> {}
            }
        }
    }

    override fun onPause() {
        super.onPause()
        with(binding){
            requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                .putBoolean("sClassNoEnable",sClassNoEnable.isChecked )
                .putBoolean("sNotifiedEnable",sNotifiedEnable.isChecked )
                .putBoolean("sNewClassEnable",sNewClassEnable.isChecked )
                .putBoolean("sClassScheNoti",sClassScheNoti.isChecked )
                .putBoolean("sWorkScheNoti",sWorkScheNoti.isChecked )
                .putBoolean("sPrivacyShowEnable",sPrivacyShowEnable.isChecked )
                .putBoolean("sSocialFollowEnabled",sSocialFollowEnabled.isChecked )
                .putBoolean("sSocialAllowInfoAccess",sSocialAllowInfoAccess.isChecked )
                .putBoolean("sSocialAllowFansCountsAccess",sSocialAllowFansCountsAccess.isChecked )
                .putString("sNickName",viewModel?.nickName?.value)
                .putString("sIntro",viewModel?.Intro?.value)
                .apply()
           // Toast.makeText(requireContext(), getString(R.string.toastSavePreference), Toast.LENGTH_SHORT).show()
        }
    }
}