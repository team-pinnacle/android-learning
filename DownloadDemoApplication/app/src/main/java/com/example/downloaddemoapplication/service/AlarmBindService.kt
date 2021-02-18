package com.example.downloaddemoapplication.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import java.util.*

class AlarmBindService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    fun startAlarmAndRetunRandomNumber(): Int {
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.start()
        return Random().nextInt(100)
    }

    override fun onBind(intent: Intent): IBinder {

        return localBinder
    }

    override fun onDestroy() {
        Log.i("AlarmBindService", "onDestroy")
        super.onDestroy()
        mediaPlayer.stop()
    }

    inner class LocalAlarmBinder : Binder(){
        fun getBindService(): AlarmBindService = this@AlarmBindService
    }

    val localBinder = LocalAlarmBinder()





}