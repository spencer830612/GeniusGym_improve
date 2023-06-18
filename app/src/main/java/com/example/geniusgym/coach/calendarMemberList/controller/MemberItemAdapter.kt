package com.example.geniusgym.coach.calendarMemberList.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberList.model.MemberItem
import com.example.geniusgym.databinding.FragmentCoCalendarMemberCardviewBinding


class MemberViewModel : ViewModel() {
    val item: MutableLiveData<MemberItem> by lazy { MutableLiveData<MemberItem>() }
}

class MemberItemAdapter(private var items: List<MemberItem>, var coActivity: CoActivity) :
    RecyclerView.Adapter<MemberItemAdapter.MemberItemViewHolder>() {

    class MemberItemViewHolder(val itemViewBinding: FragmentCoCalendarMemberCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberItemViewHolder {

        val itemViewBinding = FragmentCoCalendarMemberCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = MemberViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MemberItemViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MemberItemViewHolder, position: Int) {
        val item = items[position]
        println(item.memberId)
        with(holder) {
            itemViewBinding.viewModel?.item?.value = item
            itemView.setOnClickListener {
                coActivity.binding.viewModel?.member?.value = item
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_coCalendarMemberListFragment2_to_coCalendarMemberDetailFragment,
                    )
            }
        }
    }
    fun updateItem(items: List<MemberItem>){
        this.items = items
        notifyDataSetChanged()
    }
}