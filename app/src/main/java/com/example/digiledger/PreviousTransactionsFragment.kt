package com.example.digiledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.digiledger.databinding.FragmentPreviousTransactionsBinding
import com.example.digiledger.databinding.FragmentTransactionDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PreviousTransactionsFragment : Fragment() {

    private lateinit var viewModel: PreviousTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_previous_transactions, container, false)

        val adapter = RecordsListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(PreviousTransactionViewModel::class.java)
        viewModel.getAll.observe(viewLifecycleOwner, Observer { record -> adapter.setData(record) })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{
            val action = PreviousTransactionsFragmentDirections.actionPreviousTransactionsFragmentToTransactionFragment()
            this.findNavController().navigate(action)
        }
        return view
    }



//    private var _binding: FragmentPreviousTransactionsBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: PreviousTransactionViewModel by activityViewModels {
//        PreviousTransactionFactory(
//            (activity?.application as TransactionDatabaseInstance).database.previousTransactionDao()
//        )
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentPreviousTransactionsBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val adapter = RecordsListAdapter{}
//
//        binding.recyclerView.adapter = adapter
//        viewModel.allRecords.observe(this.viewLifecycleOwner){records -> records.let{
//            adapter.submitList(it)
//            }
//        }
//        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
//
//        binding.floatingActionButton.setOnClickListener{
//            val action = PreviousTransactionsFragmentDirections.actionPreviousTransactionsFragmentToTransactionFragment()
//            this.findNavController().navigate(action)
//        }
//
//    }
}