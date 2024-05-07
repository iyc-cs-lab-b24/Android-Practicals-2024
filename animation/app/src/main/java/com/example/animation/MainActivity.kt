package com.example.animation

import androidx.appcompat.app.AppCompatActivity
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.view.animation.AnimationUtils


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // assigning id of button which rotates
        // the image in clockwise direction
        val clk_button: Button = findViewById(R.id.clk_rotate_button)

        // assigning id of button which rotates
        // the image in anti-clockwise direction
        val anticlk_button: Button = findViewById(R.id.anticlk_rotate_button)

        // assigning id of imageview
        // which is to be rotated
        val image: ImageView = findViewById(R.id.imageView)

        // actions to be performed when
        // "rotate clockwise" button is clicked
        clk_button.setOnClickListener()
        {

            // loading the animation of
            // rotate_clockwise.xml file into a variable
            val clk_rotate = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_clockwise
            )

            // assigning that animation to
            // the image and start animation
            image.startAnimation(clk_rotate)
        }

        // actions to be performed when
        // "rotate anticlockwise" button is clicked
        anticlk_button.setOnClickListener()
        {

            // loading the animation of
            // rotate_anticlockwise.xml file into a variable
            val anticlk_rotate = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_anticlockwise
            )

            // assigning that animation to
            // the image and start animation
            image.startAnimation(anticlk_rotate)
        }

    }
}