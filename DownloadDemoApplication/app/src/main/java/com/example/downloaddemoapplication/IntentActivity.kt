package com.example.downloaddemoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class IntentActivity : AppCompatActivity() {

   lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        button = findViewById(R.id.StartActivityButton)
    }

     fun startActivityWithIntent( view: View){

        Intent(this, ActivityB::class.java).run {
            startActivity(this)
        }
    }
}