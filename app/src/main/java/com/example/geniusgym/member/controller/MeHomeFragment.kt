package com.example.geniusgym.member.controller

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geniusgym.databinding.FragmentMeHomeBinding
import com.example.geniusgym.member.viewmodel.MeHomeViewModel
import com.example.geniusgym.sharedata.MeShareData.LocalDateTimeInitHours
import com.example.geniusgym.sharedata.MeShareData.LocalDateTimeInitMinutes
import com.example.geniusgym.sharedata.MeShareData.LocalDateTimeInitSeconds
import com.example.geniusgym.sharedata.MeShareData.CountDownPerSecond
import com.example.geniusgym.sharedata.MeShareData.LocalDateTimeToTextFormat
import com.example.geniusgym.sharedata.MeShareData.CountDownTotalSecond
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class MeHomeFragment : Fragment(), CoroutineScope by MainScope() {


    private val viewModel: MeHomeViewModel by viewModels()
    private lateinit var binding : FragmentMeHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeHomeBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {

            while (true){
                withContext(Dispatchers.Main){
                    binding.ivMeHomeQRcode.setImageBitmap(mLoad("https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=123456789&margin=25"))
                }
                val fiveMinutes = LocalTime.of(LocalDateTimeInitHours, LocalDateTimeInitMinutes, LocalDateTimeInitSeconds)
                val mutex = Mutex()
                var count = 0L
                repeat(CountDownTotalSecond){
                    delay(CountDownPerSecond)
                    mutex.withLock {
                        count++
                    }
                    withContext(Dispatchers.Main){
                        binding.tvMEHomeTimer.text = fiveMinutes.minusSeconds(count).format(DateTimeFormatter.ofPattern(LocalDateTimeToTextFormat))
                    }
                }
                if (count == CountDownTotalSecond.toLong()){
                    withContext(Dispatchers.Main){
                        binding.ivMeHomeQRcode.setImageBitmap(mLoad("https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=987654321&margin=25"))
                    }
                }
            }
        }
    }



    private suspend fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            var bufferedInputStream: BufferedInputStream? = null
            withContext(Dispatchers.IO) {
                connection = url.openConnection() as HttpURLConnection
                connection.connect()
                val inputStream: InputStream? = connection.inputStream
                bufferedInputStream = BufferedInputStream(inputStream)

            }
            return BitmapFactory.decodeStream(bufferedInputStream)
                .also { bufferedInputStream?.close() }
                .also { connection?.disconnect() }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancel()
    }
}