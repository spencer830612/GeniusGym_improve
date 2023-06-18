package com.example.geniusgym.member.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.databinding.RecycleCellMeCoachinfoBinding
import com.example.geniusgym.member.model.CoachBean
import java.io.ByteArrayInputStream

class MeCoachInfoAdapter(val coaches: List<CoachBean>) : RecyclerView.Adapter<MeCoachInfoAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RecycleCellMeCoachinfoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecycleCellMeCoachinfoBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coaches.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding){
            coachName.text = coaches[position].c_name
            if (coaches[position].c_pic == null){
                coachPicture.setImageResource(R.drawable.a005)
            }else{
                val bis = ByteArrayInputStream(coaches[position].c_pic)
                val drawable = BitmapFactory.decodeStream(bis)
                coachPicture.setImageBitmap(drawable)
            }


            this.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("CoachInfo", coaches[position])
                root.findNavController().navigate(R.id.action_meCoachInfoFragment_to_meCoachInfoDetailFragment, bundle)
            }

        }

    }

}