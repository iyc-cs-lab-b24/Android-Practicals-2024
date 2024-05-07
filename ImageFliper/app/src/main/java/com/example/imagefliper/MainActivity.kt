package com.example.imagefliper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ViewFlipper


class MainActivity : AppCompatActivity()
{
    lateinit var viewFlipper: ViewFlipper
    lateinit var prevBtn: Button
    lateinit var nextBtn: Button
    lateinit var visitCourseBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initializing variables of view flipper
        // and button with their ids.
        viewFlipper = findViewById(R.id.idViewFlipper)
        prevBtn = findViewById(R.id.idBtnPrev)
        nextBtn = findViewById(R.id.idBtnNext)
        visitCourseBtn = findViewById(R.id.idBtnViewCourse)


        // on below line adding on click listener
        // for our visit course button.
        visitCourseBtn.setOnClickListener {
            // on below line we are creating intent
            // for opening a new course in chrome tab.
            val openCourse = Intent(android.content.Intent.ACTION_VIEW)
            // on below line we are setting the
            // url which we have to open.
            openCourse.data = Uri.parse("https://developer.android.com/courses")
            // on below line we are calling start
            // activity to start a new activity.
            startActivity(openCourse)
        }
        // adding on click listener
        // for our previous button.
        prevBtn.setOnClickListener {
            // on below line we are simply calling
            // view flipper to show previous screen.
            viewFlipper.showPrevious()
        }


        // adding on click listener
        // for our next button.
        nextBtn.setOnClickListener {
            // on below line we are simply calling
            // view flipper to show next screen.
            viewFlipper.showNext()
        }
    }
}
