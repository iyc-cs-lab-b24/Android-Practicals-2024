package com.example.registrationform

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.button)

        submitButton.setOnClickListener {
            // Display a toast message when the submit button is clicked
            Toast.makeText(this, "Hello Charul Ma'am Form submitted successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
