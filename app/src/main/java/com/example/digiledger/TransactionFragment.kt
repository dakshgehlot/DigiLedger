package com.example.digiledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digiledger.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.calculate_button).setOnClickListener(this)
    }

    private fun calculateTransaction() {
        val enteredBill = binding.billInput.text.toString().toIntOrNull()
        val rsTen = binding.rsTenInput.text.toString().toIntOrNull()
        val rsTwenty = binding.rsTwentyInput.text.toString().toIntOrNull()
        val rsFifty = binding.rsFiftyInput.text.toString().toIntOrNull()
        val rsHundred = binding.rsHundredInput.text.toString().toIntOrNull()
        var customerName = binding.customerNameInput.text.toString()

        val inputList: Array<Int?> = arrayOf(enteredBill, rsTen, rsTwenty, rsFifty, rsHundred)

        for (element in 0..4) {
            if (inputList[element] == null) {
                inputList[element] = 0
            }
        }

        if (customerName == ""){
            customerName = "-N/A-"
        }

        val amountReceived = (inputList[1]?.times(10)!!).plus((inputList[2]?.times(20)!!))
            .plus((inputList[3]?.times(50)!!)).plus((inputList[4]?.times(100)!!))
        val returnChange = amountReceived - inputList[0]!!

        Toast.makeText(this.activity, "Here are the details!", Toast.LENGTH_SHORT).show()
        goToNextScreen(inputList[0], amountReceived, returnChange, customerName)
    }

    private fun goToNextScreen(BILL: Int?, AMTRECEIVED: Int?, CHANGE: Int?, CUSTOMER: String) {

        val bundle = bundleOf(
            "billAmount" to BILL,
            "amountReceived" to AMTRECEIVED,
            "change" to CHANGE,
            "custName" to CUSTOMER
        )

        navController!!.navigate(
            R.id.action_transactionFragment_to_transactionDetailsFragment,
            bundle
        )
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.calculate_button -> {
                calculateTransaction()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}