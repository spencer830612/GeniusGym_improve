package com.example.geniusgym.member.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.databinding.FragmentMeTrainingMuscleMaincataCardviewBinding
import com.example.geniusgym.member.viewmodel.MeTrainingMuscleViewModel
import com.example.geniusgym.member.model.WorkoutMainItem

class MeTrainingMuscleMainAdapter(private var items: List<WorkoutMainItem>) :
    RecyclerView.Adapter<MeTrainingMuscleMainAdapter.MuMainItemViewHolder>() {
    class MuMainItemViewHolder(val itemViewBinding: FragmentMeTrainingMuscleMaincataCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuMainItemViewHolder {
        val itemViewBinding = FragmentMeTrainingMuscleMaincataCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = MeTrainingMuscleViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MuMainItemViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MuMainItemViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()

        with(holder) {
            itemViewBinding.viewModel?.item?.value = item
            bundle.putSerializable("id", item.sb_name)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
//                    .navigate(R.id.)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<WorkoutMainItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}
