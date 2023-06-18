package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.geniusgym.R
import com.example.geniusgym.member.MeMemberViewModel

class MeMemberFragment : Fragment() {

    private lateinit var viewModel: MeMemberViewModel
    private lateinit var navHostFragment : NavHostFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_me_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostFragment = NavHostFragment.create(R.navigation.navigation_me_member)
        childFragmentManager.beginTransaction()
            .replace(R.id.navigation_me_member, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()

    }
}