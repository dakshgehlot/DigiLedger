package com.example.digiledger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digiledger.databinding.FragmentTransactionDetailsBinding
import kotlin.math.absoluteValue

class TransactionDetailsFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var _binding: FragmentTransactionDetailsBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: PreviousTransactionViewModel by activityViewModels {
//        PreviousTransactionFactory(
//            (activity?.application as TransactionDatabaseInstance).database.previousTransactionDao()
//        )
//    }

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

        putData(bill, amtReceived, giveback, custName)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.detailsToHistoryButton.setOnClickListener (this)
    }

    private fun putData(BILL: Int, AMTRECEIVED: Int, CHANGE: Int, CUSTOMER: String?){
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

//        addNewRecords()
    }

//    private fun addNewRecords(){
//        viewModel.addNewRecord(
//            binding.custNameEditText.text.toString(),
//            binding.finalBillEditText.text.toString().toDouble(),
//        )
//    }

    override fun onClick(v: View?) {
        when(v!!.id){
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

