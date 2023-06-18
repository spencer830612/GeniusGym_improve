package com.example.geniusgym.member.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeBranchBinding
import com.example.geniusgym.member.MeActivity
import com.example.geniusgym.member.viewmodel.MeBranchViewModel

class MeBranchFragment : Fragment() {
    private lateinit var binding: FragmentMeBranchBinding
    private val viewModel: MeBranchViewModel by viewModels()
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var activity : MeActivity
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBranchBinding.inflate(LayoutInflater.from(requireContext()))
        activity = requireActivity() as MeActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        動態載入navigation，要做可以返回的navigation
        navHostFragment = NavHostFragment.create(R.navigation.navigation_me_branch)
        childFragmentManager.beginTransaction()
            .replace(R.id.nav_me_branch, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
//        設定topLevelDestination的Fragment，但經過測試發現如果用動態載入的navigation，
//        會沒辦法使用預設的返回，因此將所有fragment置頂
        val fragments  = mutableSetOf<Int>( R.id.meBranchDetailFragment, R.id.meCoachInfoFragment,
            R.id.meMapDirectFragment,  R.id.meShoppingFragment, R.id.meCoachInfoDetailFragment,
            R.id.meCheckoutFragment, R.id.meShopCartFragment, R.id.meShoppingDetailFragment)
        appBarConfiguration = AppBarConfiguration(fragments)

    }

    override fun onResume() {
        super.onResume()
//        因為是動態載入，因此要到onResume才可以調用navController
        navController = Navigation.findNavController(requireView().findViewById(R.id.nav_me_branch))
        setupActionBarWithNavController(
            activity,
            navController,
            appBarConfiguration
        )

    }

}