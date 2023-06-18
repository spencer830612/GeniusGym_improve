package com.example.geniusgym.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R

class CommentAdapter(private val commentsList: List<Comments>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.social_comment_layout, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentComment = commentsList[position]
        holder.avatarImageView.setImageResource(currentComment.avatarImageView)
        holder.nameTextView.text = currentComment.nameTextView
        holder.timeTextView.text = currentComment.timeTextView
        holder.messageTextView.text = currentComment.messageTextView
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}

