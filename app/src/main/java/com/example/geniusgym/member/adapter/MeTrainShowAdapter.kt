package com.example.geniusgym.member.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.databinding.MepersonaltraincardviewBinding
import com.example.geniusgym.member.model.MetrainBean

// viewmodel 要重改,新命名為MeTrainShowViewModel

class MeTrainShowViewModel:ViewModel() {
    val project : MutableLiveData<MetrainBean> by lazy {MutableLiveData<MetrainBean>()}
}

class MeTrainShowAdapter (private var items: List<MetrainBean>):
    RecyclerView.Adapter<MeTrainShowAdapter.MeTrainShowViewHolder>() {

        class MeTrainShowViewHolder(val itemViewBinding: MepersonaltraincardviewBinding ):
        RecyclerView.ViewHolder( itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeTrainShowViewHolder {
        val itemViewBinding = MepersonaltraincardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel =MeTrainShowViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MeTrainShowViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MeTrainShowViewHolder, position: Int) {
        val item = items[position]
        val bundle = Bundle()
        with(holder){
            itemViewBinding.viewModel?.project?.value = item
            bundle.putSerializable("metrain", item)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

        fun updateItem(items:List<MetrainBean>){
           this.items = items
            notifyDataSetChanged()
    }
    }

