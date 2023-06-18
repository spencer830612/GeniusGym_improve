package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordOxViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberOxBigcataCardviewBinding

class CoCaMeReOxAdapter(private var items: List<SportSmallItem>) :
    RecyclerView.Adapter<CoCaMeReOxAdapter.OxBigItemViewHolder>() {
    class OxBigItemViewHolder(val itemViewBinding: FragmentCoCalendarMemberOxBigcataCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OxBigItemViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberOxBigcataCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = CoCalenderMemberRecordOxViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OxBigItemViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: OxBigItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder) {
            itemViewBinding.viewModel?.item?.value = item
            bundle.putSerializable("item", item)
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_coCalenderMemberRecordFragment_to_coCalenderMemberRecordAfterFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<SportSmallItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}