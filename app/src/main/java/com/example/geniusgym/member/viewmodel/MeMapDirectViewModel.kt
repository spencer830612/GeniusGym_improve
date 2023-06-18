package com.example.geniusgym.member.viewmodel

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.geniusgym.sharedata.MeShareData.MapZoom
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class MeMapDirectViewModel : ViewModel() {


    //    取得String並轉換成地址的經緯度位置並回傳
    fun geocode(locationName: String, context : Context): Address? {
        val geocoder = Geocoder(context)
        var addressList: List<Address?>? = null
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocationName(locationName, 1
                ) {
                    addressList = it
                }
            }else{
                addressList = geocoder.getFromLocationName(locationName, 1)
            }
        } catch (e: IOException) {
            Log.e("GeocodeError", e.toString())
        }
        return if (addressList == null || addressList!!.isEmpty()) {
            null
        } else {
            addressList!![0]
        }
    }

    fun moveMap(latLng: LatLng, map: GoogleMap) {
        val cameraPosition = CameraPosition.builder()
            .target(latLng)
            .zoom(MapZoom)
            .build()
        val cameraUpdate = CameraUpdateFactory
            .newCameraPosition(cameraPosition)
        map.animateCamera(cameraUpdate)
    }

}