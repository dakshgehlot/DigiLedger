package com.example.digiledger

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.digiledger.databinding.TransactionDetailsBinding
import kotlin.math.absoluteValue


class TransactionDetails: AppCompatActivity(){
    private lateinit var binding: TransactionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TransactionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val bill = bundle!!.getDouble("bill_amount")
        val amtReceived = bundle.getDouble("amount_received")
        val giveback = bundle.getDouble("change")
        val custName = bundle.getString("custName")

        putData(bill, amtReceived, giveback, custName)

        title = "Transaction Details"
        val window: Window = this.window
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.teal_secondary)
    }

    private fun putData(BILL: Double, AMTRECEIVED: Double, CHANGE: Double, CUSTOMER: String?){
        val billString = BILL.toString()
        val amtReceivedString = AMTRECEIVED.toString()

        binding.custNameEditText.text = getString(R.string.customer_name, CUSTOMER)
        binding.finalBillEditText.text = getString(R.string.final_bill, billString)
        binding.cashReceivedEditText.text = getString(R.string.cash_received, amtReceivedString )

        if(CHANGE < 0){
            val positiveValue = CHANGE.absoluteValue
            val changeString = positiveValue.toString()
            binding.returnToGive.text = getString(R.string.amount_left)
            binding.returnToGiveEditText.text = getString(R.string.change, changeString)
        }
        else{
            val changeString = CHANGE.toString()
            binding.returnToGive.text = getString(R.string.change_to_return)
            binding.returnToGiveEditText.text = getString(R.string.change, changeString)
        }

    }

}