package com.example.geniusgym.coach

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geniusgym.coach.calendarMemberList.viewmodel.CoHomeViewModel
import com.example.geniusgym.databinding.FragmentCoHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

class CoHomeFragment : Fragment() {

    private lateinit var binding: FragmentCoHomeBinding
    lateinit var timer: CountDownTimer
    lateinit var timer1: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CoHomeViewModel by viewModels()
        binding = FragmentCoHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fiveMinutes = LocalTime.of(0, 0, 10)
        var now = LocalTime.now()
        val nowFormatter = DateTimeFormatter.ofPattern("hh:mm:ss")
        val formatter = DateTimeFormatter.ofPattern("mm:ss")
        val second = 1000L
        val minute = 60 * second
        val id = "example"
        var mImage: Bitmap?
        var nowString = now.format(nowFormatter)
        var url =
            "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + id + "_" + nowString + "&margin=25"
        runBlocking {
            mImage = mLoad(url)
        }

        binding.ivCoHomeQRCode.setImageBitmap(mImage)
        timer1 = object : CountDownTimer(5 * minute, 1 * second) {
            // 每過一秒，該方法會被呼叫一次
            override fun onTick(millisUntilFinished: Long) {
                val secondsUntilFinished = millisUntilFinished / second
                fiveMinutes = fiveMinutes.minusSeconds(1)
                binding.tvCoHomeHead.text = fiveMinutes.format(formatter)
            }

            // 計時器結束時，該方法會被呼叫
            override fun onFinish() {
                fiveMinutes = LocalTime.of(0, 5, 0)
                now = LocalTime.now()
                nowString = now.format(nowFormatter)
                url = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + id + "_" + nowString + "&margin=25"
                runBlocking {
                    mImage = mLoad(url)
                }
                binding.ivCoHomeQRCode.setImageBitmap(mImage)
                timer1.start()
            }
        }
        timer = object : CountDownTimer(10 * second, 1 * second) {
            // 每過一秒，該方法會被呼叫一次
            override fun onTick(millisUntilFinished: Long) {
                val secondsUntilFinished = millisUntilFinished / second
                fiveMinutes = fiveMinutes.minusSeconds(1)
                binding.tvCoHomeHead.text = fiveMinutes.format(formatter)
            }

            // 計時器結束時，該方法會被呼叫
            override fun onFinish() {
                fiveMinutes = LocalTime.of(0, 5, 0)
                now = LocalTime.now()
                nowString = now.format(nowFormatter)
                url = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + id + "_" + nowString + "&margin=25"
                runBlocking {
                    mImage = mLoad(url)
                }
                binding.ivCoHomeQRCode.setImageBitmap(mImage)
                timer1.start()
            }
        }
        timer.start() // 開始計時


    }

    private suspend fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?

        try {
            var bufferedInputStream: BufferedInputStream? = null
            withContext(Dispatchers.IO) {
                connection = url.openConnection() as HttpURLConnection
                connection.connect()
                val inputStream: InputStream? = connection?.inputStream
                bufferedInputStream = BufferedInputStream(inputStream)
            }
            return BitmapFactory.decodeStream(bufferedInputStream)
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
}