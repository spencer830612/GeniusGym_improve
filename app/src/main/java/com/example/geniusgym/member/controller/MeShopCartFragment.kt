package com.example.geniusgym.member.controller

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeShopCartBinding
import com.example.geniusgym.member.adapter.MeShoppingCartAdapter
import com.example.geniusgym.member.model.ClassInfo
import com.example.geniusgym.member.viewmodel.MeShopCartViewModel
import com.example.geniusgym.util.IOImpl
import com.google.gson.Gson
import com.google.gson.JsonObject

class MeShopCartFragment : Fragment() {

    private val viewModel: MeShopCartViewModel by viewModels()
    private lateinit var binding: FragmentMeShopCartBinding
    private lateinit var adapter: MeShoppingCartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeShopCartBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        判定是直接購買還是點擊購物車
        if (arguments?.getBoolean("direct?") == true){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable("classinfo", ClassInfo::class.java)
                    ?.let { viewModel.classInfos.value?.add(it) }
            }else{
                val list = mutableListOf<ClassInfo>()
                list.add(arguments?.getSerializable("classinfo") as ClassInfo)
                viewModel.classInfos.value = list
            }
        }else{
            val cartListText = IOImpl.Internal(requireContext()).loadArrayFile("meShoppingCart",IOImpl.Mode.MODE_MEMORY, true)
            val cartList : MutableList<ClassInfo>  = mutableListOf()
            cartListText?.forEach {
                val jsonObject = Gson().fromJson(it, JsonObject::class.java)
                val classInfo = ClassInfo()
                with(jsonObject){
                    classInfo.sc_id = get("sc_id").asInt
                    classInfo.ci_name = get("ci_name").asString
                    classInfo.c_id = get("c_id").asString
                    classInfo.ci_date = get("c_date").asString
                    classInfo.ci_cost = get("c_cost").asInt
                    classInfo.ci_ed_time = get("ci_ed_time").asString
                    classInfo.ci_start_time = get("ci_ed_time").asString
                }
                cartList.add(classInfo)
            }
            viewModel.classInfos.value = cartList
        }
        with(binding){

            val adapter = MeShoppingCartAdapter(viewModel.classInfos.value!!)
            Log.d("CartAdapter", viewModel.classInfos.value.toString())
            rcMeShoppingCart.adapter = adapter
            rcMeShoppingCart.layoutManager = LinearLayoutManager(requireContext())
            cbMeShoppingAllCheck.setOnClickListener {
                if (cbMeShoppingAllCheck.isChecked){
                    adapter.allCheck()
                }else{
                    adapter.clear()
                }

            }
            btMeShoppingConfirmBuy.setOnClickListener {
                val buyList : ArrayList<ClassInfo> = ArrayList(adapter.getCheckSet())
                val bundle = Bundle()
                bundle.putParcelableArrayList("buyItems", buyList)
                Navigation.findNavController(it).navigate(R.id.action_meShopCartFragment_to_meCheckoutFragment, bundle)
            }
        }
    }
}