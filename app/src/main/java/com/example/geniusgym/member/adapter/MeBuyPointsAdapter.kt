package com.example.geniusgym.member.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.geniusgym.R
import com.example.geniusgym.business.BuActivity
import com.example.geniusgym.databinding.FragmentMeBuyPointsBinding
import com.example.geniusgym.member.MeCreditCardActivity
import com.example.geniusgym.member.model.MeBuyPointBean



class MeBuyPointsShowViewModel : ViewModel(){
    val pointbuy  : MutableLiveData<MeBuyPointBean> by lazy { MutableLiveData<MeBuyPointBean>() }

    fun goToCreditCard(view : View, item : MeBuyPointBean){
        val intent = Intent(view.context, MeCreditCardActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("saveItem", item)
        intent.putExtra("savebundle", bundle)
        view.context.startActivity(intent)
    }
}

class MeBuyPointsAdapter(private var items : MutableList<MeBuyPointBean> ) :
    RecyclerView.Adapter<MeBuyPointsAdapter.MePointsShowViewHolder>(){

    class MePointsShowViewHolder (val itemViewBinding: FragmentMeBuyPointsBinding):
          RecyclerView.ViewHolder(itemViewBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeBuyPointsAdapter.MePointsShowViewHolder {
       val itemViewBinding = FragmentMeBuyPointsBinding.inflate(LayoutInflater.from(parent.context),parent,false )

        itemViewBinding.viewmodel = MeBuyPointsShowViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MePointsShowViewHolder(itemViewBinding)}

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MeBuyPointsAdapter.MePointsShowViewHolder, position: Int) {
        val item = items[position]

        with(holder.itemViewBinding){
            viewmodel?.pointbuy?.value = item
            btBuyPoint.setOnClickListener {
                Log.d("creditCard", item.toString())
                viewmodel?.goToCreditCard(it, item)

            }}

        }


//    fun updateItem(items: MutableList<MeBuyPointBean>){
//        this.items = items
//        notifyDataSetChanged() }
    }
































