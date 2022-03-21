package com.example.digiledger

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_secondary)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val appName = findViewById<TextView>(R.id.app_name)
        appName.alpha = 0f
        appName.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}