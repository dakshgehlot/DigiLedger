package com.example.digiledger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.data.getFormattedPrice

class RecordsListAdapter : RecyclerView.Adapter<RecordsListAdapter.RecordViewHolder>() {

    private var recordList = emptyList<PreviousTransactionRecords>()

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_list_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val currentRecord = recordList[position]
        holder.itemView.findViewById<TextView>(R.id.record_id).text = currentRecord.id.toString()
        holder.itemView.findViewById<TextView>(R.id.record_customer).text =
            currentRecord.custName
        holder.itemView.findViewById<TextView>(R.id.record_bill).text =
            currentRecord.getFormattedPrice()
    }

    fun setData(previousTransactionRecords: List<PreviousTransactionRecords>) {
        this.recordList = previousTransactionRecords
        notifyDataSetChanged()
    }
}
