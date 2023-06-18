package com.example.geniusgym.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentSocialPersonalInfoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SocialPersonalInfoFragment : Fragment() {

    private lateinit var binding: FragmentSocialPersonalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隐藏 Action Bar
        hideActionBar()

        // 在这里进行个人信息界面的初始化和逻辑处理
        // 获取传递的数据
        val username = arguments?.getString(ArticleFragment.ARG_USERNAME)
        val profileImage = arguments?.getInt(ArticleFragment.ARG_PROFILE_IMAGE)
        val postContent = arguments?.getString(ArticleFragment.ARG_POST_CONTENT)
        val postImage = arguments?.getInt(ArticleFragment.ARG_POST_IMAGE)
        val likeCount = arguments?.getInt(ArticleFragment.ARG_LIKE_COUNT)
        val commentCount = arguments?.getInt(ArticleFragment.ARG_COMMENT_COUNT)
        val postTime = arguments?.getString(ArticleFragment.ARG_POST_TIME)

        // 将数据设置给相应的视图
        binding.username.text = username
        binding.profileImage.setImageResource(profileImage ?: 0)


        // 隱藏浮動操作按鈕
        val floatingActionButton = requireActivity().findViewById<FloatingActionButton>(R.id.toPostFloatingButton)
        floatingActionButton.hide()


        // 示例：设置一个点击事件来返回上一个 Fragment
        binding.turnLeft.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val socialHomeFragment = SocialHomeFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.container, socialHomeFragment)
            transaction.commit()
        }
    }


    override fun onPause() {
        super.onPause()
        showActionBar()
    }
    // 在这里添加其他需要的方法或逻辑

    // 示例：创建一个静态方法用于实例化该 Fragment
    companion object {
        private const val ARG_USERNAME = "arg_username"
        private const val ARG_PROFILE_IMAGE = "arg_profile_image"
        private const val ARG_POST_CONTENT = "arg_post_content"
        private const val ARG_POST_IMAGE = "arg_post_image"
        private const val ARG_LIKE_COUNT = "arg_like_count"
        private const val ARG_COMMENT_COUNT = "arg_comment_count"
        private const val ARG_POST_TIME = "arg_post_time"

        fun newInstance(
            username: String,
            profileImage: Int?,
            postContent: String,
            postImage: Int?,
            likeCount: Int,
            commentCount: Int,
            postTime: String
        ): SocialPersonalInfoFragment {
            return SocialPersonalInfoFragment().apply {
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