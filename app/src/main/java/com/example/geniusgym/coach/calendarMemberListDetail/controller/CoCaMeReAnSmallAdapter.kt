package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAnoxSmallViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberAnoxSmallcataCardviewBinding

class CoCaMeReAnSmallAdapter(
    private var items: List<SportSmallItem>
) :
    RecyclerView.Adapter<CoCaMeReAnSmallAdapter.OxSmallItemViewHolder>() {
    class OxSmallItemViewHolder(val itemViewBinding: FragmentCoCalendarMemberAnoxSmallcataCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OxSmallItemViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberAnoxSmallcataCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = CoCalenderMemberRecordAnoxSmallViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OxSmallItemViewHolder(itemViewBinding)
    }
    override fun onBindViewHolder(holder: OxSmallItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder){
            itemViewBinding.viewModel?.item?.value = item
            itemView.setOnClickListener {
                bundle.putSerializable("item",item)
                Navigation.findNavController(it).navigate(R.id.action_coCalenderMemberRecordAnoxSmallFragment_to_coCalenderMemberRecordOxSmallFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(items : List<SportSmallItem>){
        this.items = items
        notifyDataSetChanged()
    }

}