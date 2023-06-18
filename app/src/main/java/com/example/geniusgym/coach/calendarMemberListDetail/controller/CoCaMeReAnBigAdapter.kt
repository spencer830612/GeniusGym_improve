package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportBigItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAnoxBigViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberAnoxBigcataCardviewBinding

class CoCaMeReAnBigAdapter(private var items: List<SportBigItem>) :
    RecyclerView.Adapter<CoCaMeReAnBigAdapter.AnBigItemViewHolder>() {
    class AnBigItemViewHolder(val itemViewBinding: FragmentCoCalendarMemberAnoxBigcataCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnBigItemViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberAnoxBigcataCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = CoCalenderMemberRecordAnoxBigViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return AnBigItemViewHolder(itemViewBinding)

    }

    override fun onBindViewHolder(holder: AnBigItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder) {
            itemViewBinding.viewModel?.item?.value = item
            bundle.putSerializable("id", item.sb_id)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_coCalenderMemberRecordFragment_to_coCalenderMemberRecordAnoxSmallFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<SportBigItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}