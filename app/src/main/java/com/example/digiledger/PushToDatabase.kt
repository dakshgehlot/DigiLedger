package com.example.digiledger

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.digiledger.databinding.TransactionDetailsBinding

class PushToDatabase: Fragment() {

    private val viewModel: PreviousTransactionViewModel by activityViewModels {
        PreviousTransactionFactory(
            (activity?.application as TransactionDatabaseInstance).database.previousTransactionDao()
        )
    }
    private lateinit var binding: TransactionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TransactionDetailsBinding.inflate(layoutInflater)
    }

    private fun addNewRecords(custName: String, bill: Double){
        viewModel.addNewRecord(
            binding.custNameEditText.toString(),
            binding.finalBillEditText.toString().toDouble(),
        )
    }
}