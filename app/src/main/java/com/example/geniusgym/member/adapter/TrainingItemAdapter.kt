package com.example.geniusgym.member.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.databinding.FragmentMeTrainingCalendarBinding
import com.example.geniusgym.member.viewmodel.MeTrainingCalendarViewModel
import com.example.geniusgym.member.model.TrainingItem

class TrainingItemAdapter(private var workoutItems: List<TrainingItem>) :
    RecyclerView.Adapter<TrainingItemAdapter.TrainingItemViewHolder>() {

    class TrainingItemViewHolder(val workoutItemViewBinding: FragmentMeTrainingCalendarBinding) :
        RecyclerView.ViewHolder(workoutItemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder {

        val workoutItemViewBinding = FragmentMeTrainingCalendarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        workoutItemViewBinding.viewModel = MeTrainingCalendarViewModel()
        workoutItemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return TrainingItemViewHolder(workoutItemViewBinding)
    }

    override fun getItemCount(): Int {
        return workoutItems.size
    }

    override fun onBindViewHolder(holder: TrainingItemViewHolder, position: Int) {
        val item = workoutItems[position]
        val bundle = Bundle()
        with(holder) {
            workoutItemViewBinding.viewModel?.workoutItem
            bundle.putSerializable("Training", item)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
//                    .navigate(
//                        R.id.action_meTrainingCalendarFragment_to_meTrainingAerobicFragment,
//                        bundle
//                    )
            }
        }
    }
    fun updateItem(items: List<TrainingItem>){
        this.workoutItems = items
        notifyDataSetChanged()
    }
}