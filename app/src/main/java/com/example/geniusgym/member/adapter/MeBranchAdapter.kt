package com.example.geniusgym.member.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geniusgym.R
import com.example.geniusgym.databinding.PopupwindowMeBranchBinding
import com.example.geniusgym.databinding.RecycleCellMeBranchBinding
import com.example.geniusgym.member.model.StoreBean
import com.example.geniusgym.sharedata.MeShareData
import com.example.geniusgym.util.OnRepeatClickListener

class MeBranchAdapter(val storeBeans: List<StoreBean>) : RecyclerView.Adapter<MeBranchAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecycleCellMeBranchBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storeBeans.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvBranchTitle.text = storeBeans[holder.adapterPosition].bh_name.toString()
        holder.binding.tvPhonenumber.text = storeBeans[holder.adapterPosition].bh_cell.toString()
        holder.binding.tvAddress.text = storeBeans[holder.adapterPosition].bh_address.toString()

        holder.binding.btImgBranch.setOnClickListener(object : OnRepeatClickListener(){
            override fun onSingleClick(v: View?) {
                //            將分店名稱放到共用的文件，做資料傳遞
                MeShareData.branchName = storeBeans[holder.adapterPosition].bh_name
                Log.d("test", MeShareData.branchName)
//            設定PopupView，使用viewBinding的方式綁定
                val binding_popup = PopupwindowMeBranchBinding.inflate(LayoutInflater.from(v?.context))
                val popup_window = PopupWindow(binding_popup.root)
//            設定popupview取得focusable，當點擊popupview以外的部分就會關閉
                popup_window.isFocusable = true

                with(binding_popup){
                    btMeCoach.setOnClickListener {
                        v?.findNavController()?.navigate(R.id.action_meBranchDetailFragment_to_meCoachInfoFragment)
                        popup_window.dismiss()
                    }


                    btMeDirect.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putString("branchlocation", storeBeans[holder.adapterPosition].bh_address)
                        v?.findNavController()?.navigate(R.id.action_meBranchDetailFragment_to_meMapDirectFragment, bundle)
                        popup_window.dismiss()
                    }
                    btMeShop.setOnClickListener {
                        v?.findNavController()?.navigate(R.id.action_meBranchDetailFragment_to_meShoppingFragment)
                        popup_window.dismiss()
                    }
                }
//            設定popupViewlayout的頁面
                popup_window.width = ViewGroup.LayoutParams.WRAP_CONTENT
                popup_window.height = ViewGroup.LayoutParams.WRAP_CONTENT
//            設定popup_window顯示的位子
                popup_window.showAsDropDown(v, -65, 0)
            }

        })
//        TODO 時間格式要等可以串接了再調整，因此缺營業時間
    }

    class MyViewHolder(val binding: RecycleCellMeBranchBinding) : ViewHolder(binding.root)

}