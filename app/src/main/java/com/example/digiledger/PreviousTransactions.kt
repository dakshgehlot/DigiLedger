package com.example.digiledger

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.digiledger.databinding.ActivityMainBinding
import com.example.digiledger.databinding.HistoryListBinding

class PreviousTransactions: AppCompatActivity() {

    private var listView: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private lateinit var binding: HistoryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val custName = intArrayOf()
    }
}