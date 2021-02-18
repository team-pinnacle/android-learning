package com.example.downloaddemoapplication.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log

class AlarmService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.start()
        return START_STICKY
    }


    override fun onDestroy() {
        Log.i("AlarmService", "mediaPlayer on Destroy is called")
        mediaPlayer.stop()
        super.onDestroy()
    }

}