package com.example.geniusgym.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R

class SocialMessageAdapter(private val chatList: List<ChatList>) : RecyclerView.Adapter<SocialMessageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        private val usernameTextView: TextView = itemView.findViewById(R.id.username)
        private val messageTextView: TextView = itemView.findViewById(R.id.messageLastText)
        private val timestampTextView: TextView = itemView.findViewById(R.id.messageLastTime)

        fun bind(chatListItem: ChatList) {
            profileImage.setImageResource(chatListItem.profileImage)
            usernameTextView.text = chatListItem.username
            messageTextView.text = chatListItem.messageLastText
            timestampTextView.text = chatListItem.messageLastTime

            itemView.setOnClickListener {
                val fragmentManager = (itemView.context as FragmentActivity).supportFragmentManager
                val fragment = SocialChatRoomFragment.newInstance() // 请根据需要调整参数
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        init {
            val dividerDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.message_stroke)
            itemView.background = dividerDrawable
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_message_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatListItem = chatList[position]
        holder.bind(chatListItem)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}