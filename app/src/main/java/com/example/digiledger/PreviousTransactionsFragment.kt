package com.example.digiledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        viewModel = ViewModelProvider(this)[PreviousTransactionViewModel::class.java]
        viewModel.getAll.observe(viewLifecycleOwner, Observer { record -> adapter.setData(record) })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val action =
                PreviousTransactionsFragmentDirections.actionPreviousTransactionsFragmentToTransactionFragment()
            this.findNavController().navigate(action)
        }
        return view
    }
}