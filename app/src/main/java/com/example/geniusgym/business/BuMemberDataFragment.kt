package com.example.geniusgym.business

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.strictmode.SetUserVisibleHintViolation
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.business.adapter.BuMemberDataAdapter
import com.example.geniusgym.business.viewModel.BuMemberDataViewModel
import com.example.geniusgym.databinding.FragmentBuMemberDataBinding

class BuMemberDataFragment : Fragment() {
    private lateinit var binding: FragmentBuMemberDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuMemberDataBinding.inflate(inflater, container, false)
        val viewModel: BuMemberDataViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
           // setupMenu()
            viewModel?.inti()

            rvBuMemberData.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.members?.observe(viewLifecycleOwner) { members ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBuMemberData.adapter == null) {
                    rvBuMemberData.adapter = BuMemberDataAdapter(members)
                } else {
                    (rvBuMemberData.adapter as BuMemberDataAdapter).updateBuMember(members)
                }
            }




            fabBuAddMemberData.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_buMemberData_to_buMemberDataAdd)
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }


//    private fun setupMenu() {
//        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.bu_option_menu_filter_search, menu)
//            }
//
//            override fun onMenuItemSelected(item: MenuItem): Boolean {
//                // Validate and handle the selected menu item
//                val text = when (item.itemId) {
//                    //R.id.toolbar_search -> search()
//                    //R.id.toolbar_filter -> getString(R.string.txtMyLocation)
//                    else -> {}
//                }
//
//                return true
//            }
//
//            override fun onPrepareMenu(menu: Menu) {
//                // Handle for example visibility of menu items
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
//    }



}