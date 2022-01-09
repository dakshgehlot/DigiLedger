package com.example.digiledger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digiledger.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.new_transaction).setOnClickListener(this)
        view.findViewById<Button>(R.id.transaction_history).setOnClickListener(this)
        view.findViewById<Button>(R.id.about_us_button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.new_transaction -> navController?.navigate(R.id.action_mainFragment_to_transactionFragment)
            R.id.transaction_history -> navController?.navigate(R.id.action_mainFragment_to_previousTransactionsFragment)
            R.id.about_us_button -> navController?.navigate(R.id.action_mainFragment_to_aboutUs)
        }
    }
}