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
import com.example.geniusgym.business.model.Class_Info
import com.example.geniusgym.business.model.testClass_Info
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.business.viewModel.BuClassViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataItemBinding
import com.example.geniusgym.databinding.FragmentBuClassDataItemBinding

/**
 * 課程列表所需的Adapter
 */
class BuClassDataAdapter(private var classes: List<Class_Info>):
    RecyclerView.Adapter<BuClassDataAdapter.BuClassDataViewHolder>() {

    /**
     * 更新課程列表內容
     * @param classes 新的課程列表
     */
    fun updateBuClass(classes: List<Class_Info>) {
        this.classes = classes
        notifyDataSetChanged()
    }

    class BuClassDataViewHolder(val itemViewBinding: FragmentBuClassDataItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuClassDataViewHolder {
        val itemViewBinding = FragmentBuClassDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BuClassViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return BuClassDataViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(
        holder: BuClassDataViewHolder,
        position: Int
    ) {
        val classs = classes[position]
        with(holder) {
            // 將欲顯示的classs物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.classs?.value = classs

            // 設置背景顏色
            if (itemViewBinding.viewModel?.classs?.value!!.ci_avail == false) {
                itemViewBinding.BuClassList.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.gray
                    )
                )
            }
            val bundle = Bundle()
            bundle.putSerializable("classs", classs)
            itemView.setOnClickListener {
                // 點擊list要跳到資料顯示頁面
                Navigation.findNavController(it).navigate(R.id.buClassDataDetailFragment, bundle)
            }
        }
    }
}