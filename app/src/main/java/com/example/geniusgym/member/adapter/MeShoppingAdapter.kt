package com.example.geniusgym.member.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.databinding.RecycleCellMeShoppingBinding
import com.example.geniusgym.member.model.ClassInfo
import com.example.geniusgym.member.viewmodel.MeRecycShopViewModel

class MeShoppingAdapter(private var shopitems : List<ClassInfo>) :
    RecyclerView.Adapter<MeShoppingAdapter.MyShopingViewHolder>() {
    private var clickable : Boolean = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyShopingViewHolder {
        val binding: RecycleCellMeShoppingBinding = RecycleCellMeShoppingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        binding.viewModel= MeRecycShopViewModel()
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MyShopingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shopitems.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun update(items : List<ClassInfo>){
        shopitems = items
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyShopingViewHolder, position: Int) {
        val shopitem = shopitems[position]

        with(holder.binding.viewModel!!){
            val kind = when(shopitem.sc_id){
                1 -> "飛輪"
                2 -> "靜態"
                3 -> "心肺訓練"
                4 -> "跑步"
                5 -> "槓鈴肩推"
                6 -> "啞鈴肩推"
                7 -> "啞鈴側平舉"
                8 -> "啞鈴前平舉"
                9 -> "站姿肩推"
                10 -> "啞鈴握推"
                11 -> "槓鈴握推"
                12 -> "蝴蝶機夾胸"
                13 -> "繩索下斜夾胸"
                14 -> "槓鈴斜上推"
                else -> "查無此類"
            }
            lessonKind.value = kind
            lessonName.value = shopitem.ci_name
            coachName.value = shopitem.c_id
            startToEnd.value = shopitem.ci_start_time + "~" + shopitem.ci_ed_time
            point.value = shopitem.ci_cost.toString()
            lessondate.value = shopitem.ci_date.toString()
        }
        val bundle = Bundle()
        bundle.putSerializable("lesson", shopitem)
        if (clickable){
            holder.itemView.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_meShoppingFragment_to_MeShoppingDetailFragment, bundle)
            }
        }

    }

    fun unclickable(){
        clickable = false
    }

    class MyShopingViewHolder(val binding: RecycleCellMeShoppingBinding) : RecyclerView.ViewHolder(binding.root)

}