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
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataItemBinding

/**
 * 員工列表所需的Adapter
 */
class BuBusinessDataAdapter(private var bubuzz: List<Business>):
    RecyclerView.Adapter<BuBusinessDataAdapter.BuBusinessDataViewHolder>() {

    /**
     * 更新員工列表內容
     * @param bubuzz 新的員工列表
     */
    fun updateBuBusiness(bubuzz: List<Business>) {
        this.bubuzz = bubuzz
        notifyDataSetChanged()
    }

    class BuBusinessDataViewHolder(val itemViewBinding: FragmentBuBusinessDataItemBinding):
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return bubuzz.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuBusinessDataViewHolder {
        val itemViewBinding = FragmentBuBusinessDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BuBusinessViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return BuBusinessDataViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(
        holder: BuBusinessDataViewHolder,
        position: Int
    ) {
        val bubuz = bubuzz[position]
        with(holder){
            // 將欲顯示的buz物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.buz?.value = bubuz

            // 設置背景顏色
            if (itemViewBinding.viewModel?.buz?.value!!.b_sus == false) {
                itemViewBinding.BuBuzList.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gray2))
            }
            val bundle = Bundle()
            bundle.putSerializable("bubuz", bubuz)
            itemView.setOnClickListener {
                // 點擊list要跳到資料顯示頁面
                Navigation.findNavController(it) .navigate(R.id.buBusinessDataDetailFragment, bundle)
            }
        }
    }

}