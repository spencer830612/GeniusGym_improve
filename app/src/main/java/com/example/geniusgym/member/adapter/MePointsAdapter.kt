package com.example.geniusgym.member.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.geniusgym.R
import com.example.geniusgym.databinding.MePointsRecordCardviewBinding
import com.example.geniusgym.databinding.PopupwindowMeBranchBinding
import com.example.geniusgym.databinding.RecycleCellMeBranchBinding
import com.example.geniusgym.member.MePointsViewModel
import com.example.geniusgym.member.model.MePointBean
import com.example.geniusgym.member.model.MetrainBean
import com.example.geniusgym.member.model.StoreBean

class MePointsShowViewModel : ViewModel(){
    val havepoint : MutableLiveData<MePointBean> by lazy { MutableLiveData<MePointBean>() } }

class MePointsAdapter (private var items: MutableList<MePointBean>):
        RecyclerView.Adapter<MePointsAdapter.MePointsViewHolder>(){

        class MePointsViewHolder (val itemViewBinding: MePointsRecordCardviewBinding):
        RecyclerView.ViewHolder(itemViewBinding.root )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MePointsViewHolder {
        val itemViewBinding = MePointsRecordCardviewBinding.inflate(LayoutInflater.from(parent.context)
        ,parent,false)

        itemViewBinding.viewModel = MePointsShowViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return  MePointsViewHolder(itemViewBinding) }

    override fun onBindViewHolder(holder: MePointsViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder){
            itemViewBinding.viewModel?.havepoint?.value = item
            bundle.putSerializable("MePointBean",item) }
    }

    override fun getItemCount(): Int {
        return items.size }

  /*  *fun addData(newItem: PointItem) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)*/

    fun updateItem(items:MutableList<MePointBean>){
        this.items = items
        notifyDataSetChanged() }

        data class PointItem(val date: String, val points: String)

    }















