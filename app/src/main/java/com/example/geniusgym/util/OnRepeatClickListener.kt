package com.example.geniusgym.util

import android.view.View
import android.view.View.OnClickListener
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

abstract class OnRepeatClickListener : OnClickListener {

    private var mLastClickTime : Long = 0L
    private var timeInterval = 1000L

    override fun onClick(v: View?) {

        val nowTime = System.currentTimeMillis()
        if ((nowTime - mLastClickTime) > timeInterval || mLastClickTime == 0L){
            onSingleClick(v)
        }
        mLastClickTime = nowTime
    }

    protected abstract fun onSingleClick(v : View?)

}