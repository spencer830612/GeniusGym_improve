package com.example.geniusgym.business.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Business
import com.example.geniusgym.business.model.Notify
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.business.viewModel.BuNotificationDetailViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataItemBinding
import com.example.geniusgym.databinding.FragmentBuNotificationDataItemBinding

/**
 * 公告列表所需的Adapter
 */
class BuNotiDataAdapter(private var notifies: List<Notify>):
    RecyclerView.Adapter<BuNotiDataAdapter.BuNotiDataViewHolder>() {

    /**
     * 更新公告列表內容
     * @param notifies 新的公告列表
     */
    fun updateBuNoti(notifies: List<Notify>) {
        this.notifies = notifies
        notifyDataSetChanged()
    }

    class BuNotiDataViewHolder(val itemViewBinding: FragmentBuNotificationDataItemBinding):
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return notifies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuNotiDataViewHolder {
        val itemViewBinding = FragmentBuNotificationDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BuNotificationDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return BuNotiDataViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(
        holder: BuNotiDataViewHolder,
        position: Int
    ) {
        val notify = notifies[position]
        with(holder){
            // 將欲顯示的notify物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.notify?.value = notify

//            // 設置背景顏色
//            if (itemViewBinding.viewModel?.notify?.value!!.b_sus == false) {
//                itemViewBinding.BuNotiList.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gray))
//            }
            val bundle = Bundle()
            bundle.putSerializable("notify", notify)
            itemView.setOnClickListener {
                // 點擊list要跳到資料顯示頁面
                Navigation.findNavController(it) .navigate(R.id.buNotificationDetailFragment, bundle)
            }
        }
    }

}