package com.example.downloaddemoapplication.restcall

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.downloaddemoapplication.R

class HttpClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_client)
    }

    fun fetchData(view: View) {
        Log.i("HttpClientActivity", "fetchData is called")
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://jsonplaceholder.typicode.com/todos"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                Log.i("HttpClientActivity", response.toString())
            },
            { error ->
                Log.i("HttpClientActivity", error.toString())
            }
        )
        queue.add(jsonObjectRequest)
    }
}