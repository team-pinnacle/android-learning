package com.example.downloaddemoapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class DownloadService : Service() {

    private var percentageOfDownload: Int = 0

    fun getPercentageOfDownlaod(): Int {
        return percentageOfDownload
    }

    fun setPercentageOfDownlaod(value:Int) {
        percentageOfDownload = value
    }

    inner class DownloadServiceBinder : Binder() {
        fun getDownloadService(): DownloadService = this@DownloadService
    }

    val binder = DownloadServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.i("DowanloadService", "onStartCommand is called")
        //  Log.i("DowanloadService", mBound.toString())
        Thread {
            startDownload()
        }.also {
            it.start()
        }
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startDownload() {
        while (true) {
            Thread.sleep(1000)
            if(percentageOfDownload == 100){
                break
            }
            percentageOfDownload++
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}