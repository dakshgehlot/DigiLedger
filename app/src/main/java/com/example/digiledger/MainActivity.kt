package com.example.digiledger

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.digiledger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newTransaction.setOnClickListener {
            val intent = Intent(this, Transaction::class.java)
            startActivity(intent)
        }
        title = "DigiLedger"


        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        if(true){setTheme()}
        }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setTheme(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_secondary)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.teal_secondary)
    }

}