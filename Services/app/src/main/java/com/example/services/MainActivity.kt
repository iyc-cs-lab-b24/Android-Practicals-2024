package com.example.services


import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var start: Button? = null
    private var stop: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start = findViewById<View>(R.id.startButton) as Button
        stop = findViewById<View>(R.id.stopButton) as Button

        start!!.setOnClickListener(this)
        stop!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.startButton -> {
                // Start the service
                startService(Intent(this, NewService::class.java))
                // Show toast message
                Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()
            }
            R.id.stopButton -> {
                // Stop the service
                stopService(Intent(this, NewService::class.java))
                // Show toast message
                Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
