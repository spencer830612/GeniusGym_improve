package com.example.geniusgym.social

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentSocialHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SocialHomeFragment : Fragment() {

    private lateinit var binding: FragmentSocialHomeBinding
    private lateinit var socialHomeViewModel: SocialHomeViewModel
    private lateinit var socialHomeAdapter: SocialHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隐藏 Action Bar
        hideActionBar()

        // 顯示浮動操作按鈕
        val floatingActionButton = requireActivity().findViewById<FloatingActionButton>(R.id.toPostFloatingButton)
        floatingActionButton.show()

        socialHomeAdapter = SocialHomeAdapter(emptyList()) // Initialize with an empty list
        binding.postRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = socialHomeAdapter
        }

        socialHomeViewModel = ViewModelProvider(this)[SocialHomeViewModel::class.java]
        socialHomeViewModel.getArticles().observe(viewLifecycleOwner) { articles ->
            socialHomeAdapter.setArticles(articles)
        }

        with(binding) {
            toMessage.setOnClickListener {
                navigateTo(R.id.action_socialHomeFragment_to_socialMessageFragment)
            }
            toPostFloatingButton.setOnClickListener {
                navigateTo(R.id.action_socialHomeFragment_to_socialPostFragment)
            }
            toProfileButton.setOnClickListener {
                navigateTo(R.id.action_socialHomeFragment_to_socialProfileFragment)
            }
            customSearchButton.setOnClickListener {
                showSearch()
            }
            customSearchCancelButton.setOnClickListener {
                exitSearch()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        showActionBar()
    }

    private fun navigateTo(actionId: Int) {
        val navController = Navigation.findNavController(requireView())
        if (navController.currentDestination?.id != actionId) {
            navController.navigate(actionId)
        }
    }

    private fun showSearch() {
        with(binding) {
            customSearchEditText.visibility = View.VISIBLE
            customSearchButton.visibility = View.GONE
            customSearchCancelButton.visibility = View.VISIBLE
        }

        // 在这里执行搜索逻辑，例如清空搜索结果或显示初始数据
    }

    private fun exitSearch() {
        with(binding) {
            customSearchEditText.visibility = View.GONE
            customSearchButton.visibility = View.VISIBLE
            customSearchCancelButton.visibility = View.GONE
        }
        hideKeyboard()

        // 在这里执行清除搜索逻辑，例如清空搜索结果或恢复初始数据
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun hideActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun showActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
