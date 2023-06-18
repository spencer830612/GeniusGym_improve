package com.example.geniusgym.coach.calendarMemberList.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberList.viewmodel.CoCalendarMemberListViewModel
import com.example.geniusgym.coach.calendarMemberList.model.ClassItem
import com.example.geniusgym.databinding.FragmentCoCalendarClassCardviewBinding

class ClassItemAdapter(private var items: List<ClassItem>) :
    RecyclerView.Adapter<ClassItemAdapter.ClassItemViewHolder>() {

    class ClassItemViewHolder(val itemViewBinding: FragmentCoCalendarClassCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassItemViewHolder {

        val itemViewBinding = FragmentCoCalendarClassCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = CoCalendarMemberListViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ClassItemViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ClassItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder) {
            itemViewBinding.viewModel?.item?.value = item
            bundle.putSerializable("Class", item)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_coCalendarTestFragment_to_coCalendarMemberListFragment2,
                        bundle
                    )
            }
        }
    }
    fun updateItem(items: List<ClassItem>){
        this.items = items
        notifyDataSetChanged()
    }
}