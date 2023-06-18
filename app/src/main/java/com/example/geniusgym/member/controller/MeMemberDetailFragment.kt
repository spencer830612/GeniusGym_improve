package com.example.geniusgym.member.controller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeMemberDetailBinding
import com.example.geniusgym.member.viewmodel.MeMemberDetailViewModel


class MeMemberDetailFragment : Fragment() {
    private lateinit var binding: FragmentMeMemberDetailBinding

   // initDrawer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MeMemberDetailViewModel by viewModels()
        binding = FragmentMeMemberDetailBinding.inflate(inflater, container ,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       with(binding){
           setupMenu(view)
       }
    }

    private fun setupMenu(view: View) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.mem_optionsmenu,menu)
            }


            override fun onMenuItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.toolbar_mem_info -> {
                        val fragment = MeMemberDetailFragment()
                        Navigation.findNavController(view).navigate(R.id.meMemberDetailFragment)
                       /* requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.Con_mem_detail,fragment)
                            .commit()*/
                    }

                    R.id.toolbar_mem_class -> {
                        val fragment = MePersonalTrain()
                        Navigation.findNavController(view).navigate(R.id.mePersonalTrain)
                        /*requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.mePersonalTrain, fragment)
                            .commit()*/
                    }

                    R.id.toolbar_mem_points ->{
                       val fragment  = MePointsFragment()
                       Navigation.findNavController(view).navigate(R.id.mePointsFragment)
                    }

                    /*R.id.toolbar_mem_bodyinfo ->{
                        val fragment  = MeBodyFragment()
                        Navigation.findNavController(view).navigate(R.id.meBodyFragment)*//*
                    }*/

                    else -> {}
                }
                return true
            }

            override fun onPrepareMenu(menu: Menu) {
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}















