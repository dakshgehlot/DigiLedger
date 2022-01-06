package com.example.digiledger


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.digiledger.databinding.CurrentTransactionBinding

class Transaction: AppCompatActivity(){

    private lateinit var binding: CurrentTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CurrentTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTransaction() }
        title = "New Transaction"
        val window: Window = this.window
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.teal_secondary)
    }

     private fun calculateTransaction(){
        val enteredBill = binding.billInput.text.toString().toDoubleOrNull()
        val rsTen = binding.rsTenInput.text.toString().toDoubleOrNull()
        val rsTwenty = binding.rsTwentyInput.text.toString().toDoubleOrNull()
        val rsFifty = binding.rsFiftyInput.text.toString().toDoubleOrNull()
        val rsHundred = binding.rsHundredInput.text.toString().toDoubleOrNull()
        val customerName = binding.customerNameInput.text.toString()

        val inputList: Array<Double?> = arrayOf(enteredBill, rsTen, rsTwenty, rsFifty, rsHundred)

        for (element in 0..4){
            if(inputList[element] == null){
                inputList[element] = 0.0
            }
        }

        val amountReceived = (inputList[1]?.times(10)!!).plus((inputList[2]?.times(20)!!)).plus((inputList[3]?.times(50)!!)).plus((inputList[4]?.times(100)!!))
        val returnChange =  amountReceived - inputList[0]!!

         Toast.makeText(this, "Here are the details!", Toast.LENGTH_SHORT).show()

         goToNextScreen(enteredBill, amountReceived, returnChange, customerName)
    }

    private fun goToNextScreen(BILL: Double?, AMTRECEIVED: Double?, CHANGE: Double?, CUSTOMER: String){
        val nextIntent = Intent(this, TransactionDetails::class.java)
        nextIntent.putExtra("bill_amount", BILL)
        nextIntent.putExtra("amount_received", AMTRECEIVED)
        nextIntent.putExtra("change", CHANGE)
        nextIntent.putExtra("custName", CUSTOMER)
        startActivity(nextIntent)
    }

}