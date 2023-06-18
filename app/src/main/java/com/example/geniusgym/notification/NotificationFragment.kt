package com.example.geniusgym.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.databinding.FragmentNotificationBinding
import com.example.geniusgym.notification.adapter.NotificationAdapter

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val viewModel : NotificationViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvNotification.layoutManager = LinearLayoutManager(requireContext())

            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvNotification.adapter == null) {
                    val notificationAdapter = NotificationAdapter(items, viewModel!!)
                    rvNotification.adapter = notificationAdapter
                } else {
                    (rvNotification.adapter as NotificationAdapter).updateNotifications(items)
                }
            }

//            viewModel?.items?.observe(viewLifecycleOwner) { item ->
//                notificationAdapter.updateNotifications(item)
//            }
        }
    }
}


