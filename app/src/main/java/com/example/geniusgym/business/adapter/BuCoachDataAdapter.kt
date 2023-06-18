package com.example.geniusgym.business.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.business.model.testBuCoach
import com.example.geniusgym.business.viewModel.BuCoachViewModel
import com.example.geniusgym.databinding.FragmentBuCoachDataItemBinding


/**
 * 教練列表所需的Adapter
 */
class BuCoachDataAdapter(private var bucoaches: List<Coach>):
    RecyclerView.Adapter<BuCoachDataAdapter.BuCoachDataViewHolder>() {
    /**
     * 更新教練列表內容
     * @param bucoaches 新的教練列表
     */
    fun updateBuCoaches(bucoaches: List<Coach>) {
        this.bucoaches = bucoaches
        notifyDataSetChanged()
    }

    class BuCoachDataViewHolder(val itemViewBinding: FragmentBuCoachDataItemBinding):
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return bucoaches.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuCoachDataViewHolder {
        val itemViewBinding = FragmentBuCoachDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BuCoachViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return BuCoachDataViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(
        holder: BuCoachDataViewHolder,
        position: Int
    ) {
        val bucoach = bucoaches[position]
        with(holder) {
            with(itemViewBinding) {
                // 將欲顯示的coach物件指派給LiveData，就會自動更新layout檔案的view顯示
                viewModel?.coach?.value = bucoach

                if (itemViewBinding.viewModel?.coach?.value!!.c_sus == false) {
                    BuCoaList.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gray))
                }
                val bundle = Bundle()
                bundle.putSerializable("bucoach", bucoach)
                println(bundle)
                itemView.setOnClickListener {
                    // 點擊list要跳到資料顯示頁面
                    Navigation.findNavController(it).navigate(R.id.buCoachDataDetailFragment, bundle)
                }

//                if(viewModel?.coach?.value!!.c_sus == false){
//                    itemView.background = null
//                    itemView.setBackgroundColor(Color.DKGRAY)
//                }else{
//                    itemView.background = null
//                    itemView.setBackgroundColor(Color.WHITE)
//                }
            }
        }
    }
}