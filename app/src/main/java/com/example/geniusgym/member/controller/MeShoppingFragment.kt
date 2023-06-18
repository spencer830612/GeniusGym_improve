package com.example.geniusgym.member.controller

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.DialogShopitemBinding
import com.example.geniusgym.databinding.FragmentMeShoppingBinding
import com.example.geniusgym.member.adapter.MeShoppingAdapter
import com.example.geniusgym.member.adapter.MeShoppingSearchExpandableListViewAdapter
import com.example.geniusgym.member.viewmodel.MeShoppingViewModel
import com.example.geniusgym.sharedata.MeShareData

class MeShoppingFragment : Fragment() {

    private lateinit var binding : FragmentMeShoppingBinding
    private lateinit var containerView: ViewGroup
    private lateinit var meAdapter: MeShoppingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        containerView = container!!
        val viewModel: MeShoppingViewModel = ViewModelProvider(this)[MeShoppingViewModel::class.java]
        binding = FragmentMeShoppingBinding.inflate(LayoutInflater.from(requireContext()))
        binding.viewModel = viewModel
//        TODO: 資料重複、品項沒有間隔
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMenu()
        Log.d("test2", MeShareData.branchName)
        with(binding){
            viewModel?.branchName?.value = MeShareData.branchName
            meShoppingRecycle.layoutManager = LinearLayoutManager(requireContext())
            meAdapter = viewModel!!.shopitems.value?.let {
                Log.d( "adapter", it.toString())
                MeShoppingAdapter(
                    it
                )
            }!!
            meShoppingRecycle.adapter = meAdapter
//          TODO: 將Fragmet設定手勢，只要由左向右滑就判定要打開drawlayout

//            root.onTouchEvent()
        }

    }



    private fun setMenu(){
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_shop, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){

                    R.id.meShoppingCartIcon -> {
                        val bundle = Bundle()
                        bundle.putBoolean("direct", false)
                        Navigation.findNavController(binding.root).navigate(R.id.action_meShoppingFragment_to_meShopCartFragment, bundle)
                    }

                    R.id.meShoppingFilterIcon -> {
                        val dialog = binding.viewModel?.createDiaglog(requireContext(), meAdapter)
                        dialog?.show()
                    }
                }
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}