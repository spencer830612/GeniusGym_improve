package com.example.geniusgym.member.adapter

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
import com.example.geniusgym.databinding.FragmentMeTrainingMuscleSmallcataCardviewBinding
import com.example.geniusgym.member.model.WorkoutSmallItem
import com.example.geniusgym.member.viewmodel.MeTrainingMuscleSmallViewModel

class MeTrainingMuscleSmallAdapter(
  private var items: List<WorkoutSmallItem>
) :
RecyclerView.Adapter<MeTrainingMuscleSmallAdapter.MuSmallItemViewHolder>() {
    class MuSmallItemViewHolder(val itemViewBinding: FragmentMeTrainingMuscleSmallcataCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuSmallItemViewHolder {
        val itemViewBinding = FragmentMeTrainingMuscleSmallcataCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = MeTrainingMuscleSmallViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MuSmallItemViewHolder(itemViewBinding)
    }
    override fun onBindViewHolder(holder: MuSmallItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder){
            itemViewBinding.viewModel?.item?.value = item
            itemView.setOnClickListener {
                bundle.putSerializable("item",item.sc_id)
//                Navigation.findNavController(it).navigate(R.id.action_meTrainingMuscleFragment_to_meTrainingMuscleSmallFragment2, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(items : List<WorkoutSmallItem>){
        this.items = items
        notifyDataSetChanged()
    }

}