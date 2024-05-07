package com.example.videoplayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var switchButton: Button
    private var isPlayingFirstVideo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        videoView = findViewById(R.id.videoView)
        switchButton = findViewById(R.id.switchButton)

        // Set media controller
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Start playing the first video
        playFirstVideo()

        // Set click listener for the switch button
        switchButton.setOnClickListener {
            if (isPlayingFirstVideo) {
                playSecondVideo()
            } else {
                playFirstVideo()
            }
        }
    }

    private fun playFirstVideo() {
        val meloPath = "android.resource://" + packageName + "/" + R.raw.modime
        videoView.setVideoURI(Uri.parse(meloPath))
        videoView.requestFocus()
        videoView.start()
        isPlayingFirstVideo = true
        switchButton.text = "Switch to 2nd Video"
    }

    private fun playSecondVideo() {
        val melodyPath = "android.resource://" + packageName + "/" + R.raw.modji
        videoView.setVideoURI(Uri.parse(melodyPath))
        videoView.requestFocus()
        videoView.start()
        isPlayingFirstVideo = false
        switchButton.text = "Switch to 1st Video"
    }
}
