package com.example.geniusgym.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.databinding.RecycleCellMeShoppingCartBinding
import com.example.geniusgym.member.model.ClassInfo

class MeShoppingCartAdapter(val list: List<ClassInfo>) : RecyclerView.Adapter<MeShoppingCartAdapter.ShoppingCartViewHolder>() {

    class ShoppingCartViewHolder(val binding : RecycleCellMeShoppingCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val binding = RecycleCellMeShoppingCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingCartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    init {
        checkSet.clear()
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        with(holder.binding){
            tvMeShoppingCheckoutChoachName.text = list[position].c_id
            tvMeShoppingCheckoutDate.text = list[position].ci_date
            val kind = when(list[position].sc_id) {
                1 -> "飛輪"
                2 -> "靜態"
                3 -> "心肺訓練"
                4 -> "跑步"
                5 -> "槓鈴肩推"
                6 -> "啞鈴肩推"
                7 -> "啞鈴側平舉"
                8 -> "啞鈴前平舉"
                9 -> "站姿肩推"
                10 -> "啞鈴握推"
                11 -> "槓鈴握推"
                12 -> "蝴蝶機夾胸"
                13 -> "繩索下斜夾胸"
                14 -> "槓鈴斜上推"
                else -> "查無此類"
            }
            tvMeShoppingCheckoutKind.text = kind
            tvMeShoppingCheckoutPoint.text = list[position].ci_cost.toString()
            val time = list[position].ci_start_time + "~" + list[position].ci_ed_time
            tvMeShoppingCheckoutTime.text = time
            tvMeShoppingCheckoutLessonName.text = list[position].ci_name
            cbMeShoppingCart.isChecked = checkSet.contains(list[position])
            cbMeShoppingCart.setOnClickListener {
                if (cbMeShoppingCart.isChecked){
                    checkSet.add(list[position])
                }else{
                    checkSet.remove(list[position])
                }
            }
        }

    }

    companion object{
        val checkSet = mutableSetOf<ClassInfo>()

    }

    fun allCheck(){

        list.forEach{
            checkSet.add(it)
        }
        notifyDataSetChanged()

    }

    fun clear(){
        checkSet.clear()
        notifyDataSetChanged()
    }

    fun getCheckSet() : MutableSet<ClassInfo>{
        return checkSet
    }


}