package com.example.geniusgym.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentArticleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root

        // 隐藏 Action Bar
        hideActionBar()

        binding.commentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.commentRecyclerView.setHasFixedSize(true)

        // 获取传递的数据
        val username = arguments?.getString(ARG_USERNAME)
        val profileImage = arguments?.getInt(ARG_PROFILE_IMAGE)
        val postContent = arguments?.getString(ARG_POST_CONTENT)
        val postImage = arguments?.getInt(ARG_POST_IMAGE)
        val likeCount = arguments?.getInt(ARG_LIKE_COUNT)
        val commentCount = arguments?.getInt(ARG_COMMENT_COUNT)
        val postTime = arguments?.getString(ARG_POST_TIME)

        // 设置其他视图和逻辑

        // 将数据设置给相应的视图
        binding.username.text = username
        binding.profileImage.setImageResource(profileImage ?: 0)
        binding.postContent.text = postContent

        // 设置文章图片
        if (postImage != null) {
            binding.postImage.setImageResource(postImage)
            binding.postImage.visibility = View.VISIBLE
        } else {
            binding.postImage.visibility = View.GONE
        }

        binding.likeCount.text = likeCount.toString()
        binding.commentCount.text = commentCount.toString()
        binding.postTime.text = postTime

        // 隱藏浮動操作按鈕
        val floatingActionButton = requireActivity().findViewById<FloatingActionButton>(R.id.toPostFloatingButton)
        floatingActionButton.hide()

        binding.turnLeft.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val socialHomeFragment = SocialHomeFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.container, socialHomeFragment)
            transaction.commit()
        }

        binding.root.setOnClickListener { }

        // 创建留言数据列表
        val commentsList = listOf(
            Comments(R.drawable.eren_yeager, "John Doe", "10:00 AM", "This is a comment"),
            Comments(R.drawable.saitama, "Jane Smith", "11:30 AM", "Another comment")
        )

        // 设置留言适配器
        val commentAdapter = CommentAdapter(commentsList)
        binding.commentRecyclerView.adapter = commentAdapter

        return view
    }
    override fun onPause() {
        super.onPause()
        showActionBar()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_USERNAME = "arg_username"
        const val ARG_PROFILE_IMAGE = "arg_profile_image"
        const val ARG_POST_CONTENT = "arg_post_content"
        const val ARG_POST_IMAGE = "arg_post_image"
        const val ARG_LIKE_COUNT = "arg_like_count"
        const val ARG_COMMENT_COUNT = "arg_comment_count"
        const val ARG_POST_TIME = "arg_post_time"

        fun newInstance(
            username: String,
            profileImage: Int?,
            postContent: String,
            postImage: Int?,
            likeCount: Int,
            commentCount: Int,
            postTime: String
        ): ArticleFragment {
            return ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                    putInt(ARG_PROFILE_IMAGE, profileImage ?: 0)
                    putString(ARG_POST_CONTENT, postContent)
                    postImage?.let { putInt(ARG_POST_IMAGE, it) }
                    putInt(ARG_LIKE_COUNT, likeCount)
                    putInt(ARG_COMMENT_COUNT, commentCount)
                    putString(ARG_POST_TIME, postTime)
                }
            }
        }
    }

    private fun hideActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun showActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
