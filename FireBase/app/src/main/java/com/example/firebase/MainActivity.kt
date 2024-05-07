package com.example.firebase

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseDatabase
    private lateinit var dataNodeRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView = findViewById<TextView>(R.id.data)

        // Initialize Firebase database reference
        db = FirebaseDatabase.getInstance("https://charul-ma-am-default-rtdb.firebaseio.com/")
        dataNodeRef = db.getReference("name")

        // Read data from Firebase database
        dataNodeRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get the value from dataSnapshot
                val value = dataSnapshot.getValue(String::class.java)

                // Update TextView with the retrieved value
                dataTextView.text = "Value is: $value"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                // For simplicity, no error handling is implemented here. You might want to handle errors in your app.
            }
        })
    }

    fun change(view: View) {
        val changeData = findViewById<EditText>(R.id.changeData).text.toString()
        dataNodeRef.setValue(changeData)
    }
}
