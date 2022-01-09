package com.example.digiledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.databinding.FragmentTransactionDetailsBinding
import kotlin.math.absoluteValue

class TransactionDetailsFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var _binding: FragmentTransactionDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PreviousTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)

        val bill = arguments?.getInt("billAmount")!!
        val amtReceived = arguments?.getInt("amountReceived")!!
        val giveback = arguments?.getInt("change")!!
        val custName = arguments?.getString("custName").toString()

        viewModel = ViewModelProvider(this)[PreviousTransactionViewModel::class.java]

        putData(bill, amtReceived, giveback, custName)

        binding.saveToDb.setOnClickListener {
            if (bill == 0) {
                Toast.makeText(this.activity, "Enter Bill Amount!", Toast.LENGTH_SHORT).show()
            } else {
                insertRecordToDB(custName, bill)
                Toast.makeText(this.activity, "Details Saved!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.detailsToHistoryButton.setOnClickListener(this)
    }

    private fun putData(BILL: Int, AMTRECEIVED: Int, CHANGE: Int, CUSTOMER: String?) {
        val billString = BILL.toString()
        val amtReceivedString = AMTRECEIVED.toString()

        when(CUSTOMER){
            "-N/A-" -> binding.custNameEditText.text = CUSTOMER
            else -> binding.custNameEditText.text = getString(R.string.customer_name, CUSTOMER)
        }

        binding.finalBillEditText.text = getString(R.string.final_bill, billString)
        binding.cashReceivedEditText.text = getString(R.string.cash_received, amtReceivedString)

        if (CHANGE < 0) {
            val positiveValue = CHANGE.absoluteValue
            val changeString = positiveValue.toString()
            binding.returnToGive.text = getString(R.string.amount_left)
            binding.returnToGiveEditText.text = getString(R.string.change, changeString)
        } else {
            val changeString = CHANGE.toString()
            binding.returnToGive.text = getString(R.string.change_to_return)
            binding.returnToGiveEditText.text = getString(R.string.change, changeString)
        }
    }

    private fun insertRecordToDB(CUSTOMER: String?, BILL: Int) {
        val record = PreviousTransactionRecords(0, CUSTOMER!!, Integer.parseInt(BILL.toString()))
        viewModel.addRecord(record)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.details_to_history_button -> {
                navController!!.navigate(R.id.action_transactionDetailsFragment_to_previousTransactionsFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

