package com.example.downloaddemoapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var downloadService: DownloadService
    var lastDownloadPercentage  = 0
    private var mBound: Boolean = false
    private  var serviceAlreadyCreated = false
    private lateinit var progressBar :ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.i("MainActivity", savedInstanceState.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById(R.id.button) as Button
         progressBar = findViewById(R.id.progressbar) as ProgressBar
        button.setOnClickListener(this)


    }

    override fun onStart() {
        Log.i("MainActivity", "onStart")
        super.onStart()
        Intent(this, DownloadService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null){
            Log.i("MainActivity", savedInstanceState.toString())
            serviceAlreadyCreated = true
            lastDownloadPercentage = savedInstanceState.get("lastDownloadPercentage") as Int
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {

        outState.putInt("lastDownloadPercentage", downloadService.getPercentageOfDownlaod())
        super.onSaveInstanceState(outState, outPersistentState)
    }


    private val connection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DownloadService.DownloadServiceBinder
            downloadService =  binder.getDownloadService()
            downloadService.setPercentageOfDownlaod(lastDownloadPercentage)
            Log.i("MainActivity", lastDownloadPercentage.toString())
            mBound = true
            Thread{
                while (true){
                    progressBar.setProgress(downloadService.getPercentageOfDownlaod())
                    if (downloadService.getPercentageOfDownlaod() == 100){
                        break
                    }
                }
            }.start()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    override fun onClick(v: View?) {
            if(mBound){
                Toast.makeText(this, "Dowanload Percentage " + downloadService.getPercentageOfDownlaod(), Toast.LENGTH_LONG).show()
            }
    }
}