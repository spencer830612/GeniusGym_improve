package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAllcardViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberAllCardviewBinding

private lateinit var navController: NavController
class CoCaMeReAllAdapter(private var items: List<SportSmallItem>) :
    RecyclerView.Adapter<CoCaMeReAllAdapter.CoCaMeReAllViewHolder>() {
    class CoCaMeReAllViewHolder(val itemViewBinding: FragmentCoCalendarMemberAllCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoCaMeReAllViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberAllCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        navController = parent.findNavController()
        itemViewBinding.viewModel = CoCalenderMemberRecordAllcardViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CoCaMeReAllViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CoCaMeReAllViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder){
            itemViewBinding.viewModel?.item?.value = item
            itemView.setOnClickListener {
                bundle.putSerializable("item",item)
                Navigation.findNavController(it).navigate(R.id.action_coCalenderMemberRecordFragment_to_coCalenderMemberRecordAfterFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<SportSmallItem>){
        this.items = items
        notifyDataSetChanged()
    }
}