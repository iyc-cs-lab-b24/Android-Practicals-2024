package com.example.inplicit1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val websiteInput = findViewById<EditText>(R.id.websiteInput)
        val btnOpenWebsite = findViewById<Button>(R.id.btnOpenWebsite)
        val btnComposeEmail = findViewById<Button>(R.id.btnComposeEmail)
        val btnShowMap = findViewById<Button>(R.id.btnShowMap)

        btnOpenWebsite.setOnClickListener {
            var url = websiteInput.text.toString().trim()

            // Check if the URL starts with http:// or https://, if not, append http://
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }

            if (URLUtil.isValidUrl(url)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }


        btnComposeEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:principaliyc@yahoo.in")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            startActivity(intent)
        }

        btnShowMap.setOnClickListener {
            val locationUrl = "https://maps.google.com/?q=19.133615,72.853999" // Example coordinates for Times Square, New York
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))
            startActivity(intent)

        }
    }
}
