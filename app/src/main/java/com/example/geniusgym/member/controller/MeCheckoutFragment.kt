package com.example.geniusgym.member.controller

import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.DialogMeCheckoutBinding
import com.example.geniusgym.databinding.FragmentMeCheckoutBinding
import com.example.geniusgym.member.adapter.MeShoppingAdapter
import com.example.geniusgym.member.model.ClassInfo

import com.example.geniusgym.member.viewmodel.MeCheckoutViewModel
import com.example.geniusgym.sharedata.MeShareData
import com.example.geniusgym.util.IOImpl
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File


class MeCheckoutFragment : Fragment() {

    private val viewModel: MeCheckoutViewModel by viewModels()
    private lateinit var binding : FragmentMeCheckoutBinding
    private lateinit var containerDialog: ViewGroup
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeCheckoutBinding.inflate(LayoutInflater.from(requireContext()))
        containerDialog = container!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("buyItems", ClassInfo::class.java)?.let {
                viewModel.buylist = it
            }
        }else{
            val buylist : ArrayList<ClassInfo> = arguments?.getParcelableArrayList("buyItems")?: ArrayList()
           viewModel.buylist = buylist
        }
        with(binding){
            val adapter = MeShoppingAdapter(viewModel.buylist)
            adapter.unclickable()
            meRecycleShoppingCart.adapter = adapter
            meRecycleShoppingCart.layoutManager = LinearLayoutManager(requireContext())
            var total = 0
            viewModel.buylist.forEach{ClassInfo ->
                ClassInfo.ci_cost?.let {
                    total += it
                }
            }
            tvMeShoppingPointNeed.text = total.toString()
            meShoppingPointHave.text = MeShareData.personPoint.toString()
            btMeShoppingCheckout.setOnClickListener {
                if (MeShareData.personPoint < total){
                    tvMeShoppingError.text = getString(R.string.meShoppoingCheckoutNoMony)
                    return@setOnClickListener
                }else{
                    MeShareData.personPoint -= total
                    val cartListText = IOImpl.Internal(requireContext()).loadArrayFile("meShoppingCart",
                        IOImpl.Mode.MODE_MEMORY, true)
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
                    viewModel.buylist.forEach{
                        if (cartList.contains(it)){
                            cartList.remove(it)
                        }
                    }
                    val jsonArray = JsonArray()
                    cartList.forEach{classde ->
                        val jsonObject = JsonObject()
                        jsonObject.addProperty("c_id", classde.c_id)
                        jsonObject.addProperty("c_date", classde .ci_date)
                        jsonObject.addProperty("sc_id", classde.sc_id)
                        jsonObject.addProperty("ci_name", classde.ci_name)
                        jsonObject.addProperty("c_cost", classde.ci_cost.toString())
                        jsonObject.addProperty("ci_start_time", classde.ci_start_time)
                        jsonObject.addProperty("ci_ed_time", classde.ci_ed_time)
                        jsonArray.add(jsonObject)
                    }
                    IOImpl.Internal(requireContext()).saveFile(jsonArray, "meShoppingCart", IOImpl.Mode.MODE_MEMORY, true)
                }


//                結帳成功畫面
                val dialog = Dialog(requireContext())
                dialog.setCancelable(false)
                val bindingDialog = DialogMeCheckoutBinding.inflate(LayoutInflater.from(requireContext()), containerDialog, false)
                val window = dialog.window
                window?.setContentView(bindingDialog.root)
                val lp = window?.attributes
                lp?.width = WindowManager.LayoutParams.MATCH_PARENT
                lp?.height = WindowManager.LayoutParams.MATCH_PARENT
                bindingDialog.btMeShoppingBack.setOnClickListener {
                    Navigation.findNavController(binding.root).navigate(R.id.action_meCheckoutFragment_to_meShoppingFragment)
                    dialog.dismiss()
                }
                bindingDialog.btMeGoToShoppingRecord.setOnClickListener {
                    dialog.dismiss()
                }
                //                結帳中畫面
                val dialoganim = Dialog(requireContext())
                val windowanim = dialoganim.window
                windowanim?.setContentView(R.layout.dialog_me_loading)
                val iv : ImageView? = windowanim?.findViewById(R.id.ivMeShoppingLoading)
                iv?.setImageResource(R.drawable.loading)
                val anim = iv?.drawable as AnimationDrawable
                anim.start()
                dialoganim.show()
                val timer = object : CountDownTimer(2000, 1000){
                    override fun onTick(p0: Long) {

                    }

                    override fun onFinish() {
                        dialoganim.dismiss()
                        dialog.show()
                    }
                }
                timer.start()

            }
            btCheckoutSaveMoney.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_meCheckoutFragment_to_meBuyPointsFragment)
            }
        }

    }


}