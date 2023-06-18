package com.example.geniusgym.business

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.business.viewModel.BuNotificationDetailViewModel
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Business
import com.example.geniusgym.business.model.Notify
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataDetailBinding
import com.example.geniusgym.databinding.FragmentBuNotificationDetailBinding

class BuNotificationDataDetailFragment : Fragment() {
    private lateinit var binding: FragmentBuNotificationDetailBinding
    val url = "http://10.0.2.2:8080/geninusgym_bg/NotifyController"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuNotificationDetailBinding.inflate(inflater, container, false)
        val viewModel: BuNotificationDetailViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let { bundle ->
                bundle.getSerializable("notify")?.let {
                    viewModel?.notify?.value = it as Notify
                    println(bundle)
                }
            }

            tietBuAddNotiSender.isEnabled = false
            tietBuAddNotiSender.setBackgroundColor(Color.GRAY)
            tietBuAddNotiText.isEnabled = false
            tietBuAddNotiText.setBackgroundColor(Color.GRAY)


            btBuAddNotiDataDetailModify.setOnClickListener {
                tietBuAddNotiSender.isEnabled = true
                tietBuAddNotiSender.setBackgroundColor(Color.WHITE)
                tietBuAddNotiText.isEnabled = true
                tietBuAddNotiText.setBackgroundColor(Color.WHITE)
                btBuAddNotiDataDetailModify.visibility = View.GONE
                btBuAddNotiDataDetailSave.visibility = View.VISIBLE
            }

            btBuAddNotiDataDetailSave.setOnClickListener {
                viewModel?.notify?.value.run {
                    tietBuAddNotiSender.isEnabled = false
                    tietBuAddNotiSender.setBackgroundColor(Color.GRAY)
                    tietBuAddNotiText.isEnabled = false
                    tietBuAddNotiText.setBackgroundColor(Color.GRAY)
                    btBuAddNotiDataDetailModify.visibility = View.VISIBLE
                    btBuAddNotiDataDetailSave.visibility = View.GONE
                    //串後端
                }
            }

            btBuAddNotiCancel.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

}