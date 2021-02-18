package com.example.downloaddemoapplication.implicit.intent.learning


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.downloaddemoapplication.R

class ImplicitIntentLearning : AppCompatActivity() {
     lateinit var mapIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_learning)
    }

    fun startMapActivity(view: View){
        mapIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:17.4699,78.3578")
        }

        Intent.createChooser(mapIntent, "Select Map application").run {
            startActivity(this)
        }
    }

    fun composeMmsMessage(view: View) {
        Log.i("ImplicitIntentLearning", "composeMmsMessage")
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("smsto:")  // This ensures only SMS apps respond
            putExtra("sms_body", "Hello this is from android emulator")
            putExtra("address", "9160255893")
            //putExtra(Intent.EXTRA_STREAM, attachment)
        }

        if (intent.resolveActivity(packageManager) != null) {
            Intent.createChooser(intent, "Select Map application").run {
                startActivity(this)
            }
           // startActivity(intent)
        }
        //composeEmail(Array(1, { i -> "nitya.cse.hit@gmail.com"}), "Testing email")
    }

    fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            //putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}