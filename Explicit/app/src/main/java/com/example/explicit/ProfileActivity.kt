package com.example.explicit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val button= findViewById<Button>(R.id.button)
button.setOnClickListener {
    val intent = Intent(this,MainActivity::class.java)
    startActivity(intent)
    finish()
}


    }
}