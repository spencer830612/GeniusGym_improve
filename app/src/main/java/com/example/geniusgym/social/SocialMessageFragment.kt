package com.example.geniusgym.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentSocialMessageBinding

class SocialMessageFragment : Fragment() {
    private lateinit var binding: FragmentSocialMessageBinding
    private lateinit var socialMessageAdapter: SocialMessageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSocialMessageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隐藏 Action Bar
        hideActionBar()

        val chatList = getChatList()
        socialMessageAdapter = SocialMessageAdapter(chatList)
        binding.chatListRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = socialMessageAdapter
        }

        binding.toHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_socialMessageFragment_to_socialHomeFragment)
        }

        binding.toProfileButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_socialMessageFragment_to_socialProfileFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        showActionBar()
    }

    private fun getChatList(): List<ChatList> {
        return listOf(
            ChatList(1, R.drawable.eren_yeager, "User1", "Hello!", "9:00 AM"),
            ChatList(2, R.drawable.walter_white, "User2", "Hi there!", "10:30 AM"),
            ChatList(3, R.drawable.saitama, "User3", "Good morning!", "11:45 AM")
        )
    }

    private fun hideActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun showActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}