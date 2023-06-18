package com.example.geniusgym.notification.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.R
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.databinding.FragmentNotificationItemBinding
import com.example.geniusgym.notification.NotificationViewModel
import com.example.geniusgym.notification.model.Notification

class NotificationAdapter(
    private var notiLists: List<Notification>,
    private val viewModel: NotificationViewModel
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    fun updateNotifications(notifications: List<Notification>) {
        notiLists = notifications
        notifyDataSetChanged()
    }

    class NotificationViewHolder(val itemViewBinding: FragmentNotificationItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemViewBinding = FragmentNotificationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = NotificationViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return NotificationViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notiLists[position]
        with(holder.itemViewBinding) {
            viewModel?.item?.value = notification
            //lifecycleOwner = holder.itemView.findViewTreeLifecycleOwner()

//            val bundle = Bundle()
//            bundle.putSerializable("notification", notification)
//            root.setOnClickListener {
//                Navigation.findNavController(it).navigate(R.id.nav_controller_view_tag, bundle)
//            }// 我建議不要做跳轉<單純顯示通知即可好
        }
    }

    override fun getItemCount(): Int {
        return notiLists.size
    }
}


