package com.example.geniusgym

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.databinding.FragmentLoginForgetBinding

class LoginForgetFragment : Fragment() {
    private lateinit var binding: FragmentLoginForgetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginForgetBinding.inflate(inflater, container, false)
        val viewModel: LoginForgetViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btLoginForgetConfirm.setOnClickListener {
                val phoneNumber = binding.tietLoginForgetNumber.text.toString()
                val accountType = getAccountTypeFromLogin(phoneNumber)
                if (accountType != "unknown") {
                    sendPasswordSms(phoneNumber)
                } else {
                    Toast.makeText(requireContext(), "無效帳號類型", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getAccountTypeFromLogin(phoneNumber: String): String {
        val accountType: String

        if (phoneNumber.startsWith("business", ignoreCase = true)) {
            accountType = "business"
        } else if (phoneNumber.startsWith("member", ignoreCase = true)) {
            accountType = "member"
        } else if (phoneNumber.startsWith("coach", ignoreCase = true)) {
            accountType = "coach"
        } else {
            accountType = "unknown"
        }
        return accountType
    }

    private fun sendPasswordSms(phoneNumber: String) {
        val defaultPassword = "111111"
        val message = "Your Password: $defaultPassword"
    }

}