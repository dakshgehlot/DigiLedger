package com.example.digiledger

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PreviousTransactionAdapter(private val context: Activity, private val arrayList: ArrayList<PreviousTransactionDetails>): ArrayAdapter<PreviousTransactionDetails>(context, R.layout.history_list_items, arrayList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.history_list_items, null)

        view.findViewById<TextView>(R.id.cust_name_edit_text).text = arrayList[position].name
        view.findViewById<TextView>(R.id.final_bill_edit_text).text = arrayList[position].name
        view.findViewById<TextView>(R.id.cash_received_edit_text).text = arrayList[position].name
        view.findViewById<TextView>(R.id.return_to_give_edit_text).text = arrayList[position].name

        return view
    }
}