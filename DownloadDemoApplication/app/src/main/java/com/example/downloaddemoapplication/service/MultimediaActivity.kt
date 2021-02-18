package com.example.downloaddemoapplication.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.downloaddemoapplication.R

class MultimediaActivity : AppCompatActivity() {

     lateinit var mService: AlarmBindService
     var mBound: Boolean = false


    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            Log.i("MultimediaActivity", "onServiceConnected")
            val binder = service as AlarmBindService.LocalAlarmBinder
            mService = binder.getBindService()
            mBound = true
            Log.i("MultimediaActivity", mService.toString())
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.i("MultimediaActivity", "onServiceDisconnected")
            mBound = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia_activity)
    }

    override fun onStart() {
        super.onStart()
        Intent(this, AlarmBindService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
//        Intent(this, AlarmService::class.java).also {
//            stopService(it)
//        }
        Intent(this, AlarmBindService::class.java).also {
           unbindService(connection)
        }
        super.onDestroy()
    }

    fun playAlarm(view: View) {
        Intent(this, AlarmService::class.java).also {
            startService(it)
        }
    }

    fun stopAlarm(view: View) {
        Intent(this, AlarmService::class.java).also {
            stopService(it)
        }
    }

    fun getrandom(view: View) {
        Log.i("MultimediaActivity", mService.startAlarmAndRetunRandomNumber().toString())

        Toast.makeText(this, mService.startAlarmAndRetunRandomNumber().toString(),Toast.LENGTH_LONG ).show()

    }


}