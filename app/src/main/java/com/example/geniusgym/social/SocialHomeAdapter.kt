package com.example.geniusgym.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.databinding.SocialHomeLayoutBinding

class SocialHomeAdapter(private var items: List<Post>) : RecyclerView.Adapter<SocialHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SocialHomeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setArticles(articles: List<Post>) {
        items = articles
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: SocialHomeLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isContentEllipsized = false // 記錄文章內容是否被截斷

        fun bind(item: Post) {
            val context = binding.root.context
            val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.home_stroke)
            binding.root.background = backgroundDrawable
            binding.profileImage.setImageResource(item.profileImage)
            binding.username.text = item.username
            binding.likeCount.text = item.likeCount.toString()
            binding.commentCount.text = item.commentCount.toString()
            binding.postTime.text = item.postTime

            binding.profileImage.setOnClickListener {
                val selectedPost = items[position]
                val fragmentManager = (binding.root.context as FragmentActivity).supportFragmentManager
                val fragment = SocialPersonalInfoFragment.newInstance(
                    selectedPost.username,
                    selectedPost.profileImage,
                    selectedPost.postContent,
                    selectedPost.postImage,
                    selectedPost.likeCount,
                    selectedPost.commentCount,
                    selectedPost.postTime
                )

                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            binding.postContent.setOnClickListener {
                // 点击事件处理
                val selectedPost = items[position]
                val fragmentManager = (binding.root.context as FragmentActivity).supportFragmentManager
                val fragment = ArticleFragment.newInstance(
                    selectedPost.username,
                    selectedPost.profileImage,
                    selectedPost.postContent,
                    selectedPost.postImage,
                    selectedPost.likeCount,
                    selectedPost.commentCount,
                    selectedPost.postTime
                )
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            binding.commentBT.setOnClickListener {
                // 点击事件处理
                val selectedPost = items[position]
                val fragmentManager = (binding.root.context as FragmentActivity).supportFragmentManager
                val fragment = ArticleFragment.newInstance(
                    selectedPost.username,
                    selectedPost.profileImage,
                    selectedPost.postContent,
                    selectedPost.postImage,
                    selectedPost.likeCount,
                    selectedPost.commentCount,
                    selectedPost.postTime
                )
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            // 設置「查看更多」元素的點擊事件
            binding.readMore.setOnClickListener {
                // 点击事件处理
                val selectedPost = items[position]
                val fragmentManager = (binding.root.context as FragmentActivity).supportFragmentManager
                val fragment = ArticleFragment.newInstance(
                    selectedPost.username,
                    selectedPost.profileImage,
                    selectedPost.postContent,
                    selectedPost.postImage,
                    selectedPost.likeCount,
                    selectedPost.commentCount,
                    selectedPost.postTime
                )
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            // 設置文章內容
            binding.postContent.text = item.postContent

            // 註冊 OnLayoutChangeListener
            binding.postContent.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
                // 檢查文章內容是否被截斷
                val layout = binding.postContent.layout
                val lineCount = layout?.lineCount ?: 0
                val isEllipsized = (layout?.getEllipsisCount(lineCount - 1) ?: 0) > 0

                // 更新 isContentEllipsized
                isContentEllipsized = isEllipsized

                // 根據是否被截斷設置「查看更多」元素的可見性
                if (isContentEllipsized) {
                    binding.readMore.visibility = View.VISIBLE
                } else {
                    binding.readMore.visibility = View.GONE
                }
            }

            // 設置文章圖片
            item.postImage?.let {
                binding.postImage.setImageResource(it)
                binding.postImage.visibility = View.VISIBLE
            } ?: run {
                binding.postImage.visibility = View.GONE
            }
        }
    }
}