package com.example.geniusgym.business.core.util


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
//import android.util.Base64
import java.util.Base64
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat

//Project gradle跟Module gradle不加也能跑,老師有紅線潔癖
@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("imgBase64")
fun ImageView.setImgBase64(base64: String?){
    //如果不是空值的scope
    base64?.let {
        //前後端Base64要一樣
        val byteArray = Base64.getDecoder().decode(base64)
        //單向顯示圖片
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        setImageBitmap(bitmap)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@InverseBindingAdapter(attribute = "imgBase64")
fun ImageView.getImgBase64(): String?{
    drawable?.let{
        val stream = ByteArrayOutputStream()
        it.toBitmap().compress(Bitmap.CompressFormat.PNG,100,stream)
        val byteArray = stream.toByteArray()
        return Base64.getEncoder().encodeToString(byteArray)
    }
    return null
}

@BindingAdapter("imgBase64AttrChanged")
fun ImageView.setOnImgBase64AttrChangedListener(listener: InverseBindingListener) {
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> listener.onChange() }
                                                //大致可理解為onChange會去呼叫getImgBase64
}

val SDF = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

@BindingAdapter("text")
fun TextView.setText(timestamp: Timestamp){
    text = SDF.format(timestamp)
}

@BindingAdapter("android:onLongClick")
fun setOnLongClickListener(view: View, block : () -> Unit) {
    view.setOnLongClickListener {
        block()
        return@setOnLongClickListener true
    }
}