package com.example.digiledger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.databinding.HistoryListItemsBinding
import com.example.digiledger.data.getFormattedPrice

class RecordsListAdapter: RecyclerView.Adapter<RecordsListAdapter.RecordViewHolder>(){

    private var recordList = emptyList<PreviousTransactionRecords>()

    class RecordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_list_items, parent, false))
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val currentRecord = recordList[position]
        holder.itemView.findViewById<TextView>(R.id.record_id).text = currentRecord.id.toString()
        holder.itemView.findViewById<TextView>(R.id.record_customer).text = currentRecord.custName.toString()
        holder.itemView.findViewById<TextView>(R.id.record_bill).text = currentRecord.billAmount.toString()
    }

    fun setData(previousTransactionRecords: List<PreviousTransactionRecords>){
        this.recordList = previousTransactionRecords
        notifyDataSetChanged()
    }
}




//class RecordsListAdapter(private val onItemClicked: (PreviousTransactionRecords) -> Unit) :
//    ListAdapter<PreviousTransactionRecords, RecordsListAdapter.RecordViewHolder>(DiffCallback) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
//        return RecordViewHolder(
//            HistoryListItemsBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                )
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
//        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//            onItemClicked(current)
//        }
//        holder.bind(current)
//    }
//
//    class RecordViewHolder(private var binding: HistoryListItemsBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(previousTransactionRecords: PreviousTransactionRecords) {
//            binding.apply{
//                recordId.text = previousTransactionRecords.id.toString()
//                recordCustomer.text = previousTransactionRecords.custName
//                recordBill.text = previousTransactionRecords.getFormattedPrice()
//            }
//        }
//    }
//
//    companion object {
//        private val DiffCallback = object : DiffUtil.ItemCallback<PreviousTransactionRecords>() {
//            override fun areItemsTheSame(oldItem: PreviousTransactionRecords, newItem: PreviousTransactionRecords): Boolean {
//                return oldItem === newItem
//            }
//
//            override fun areContentsTheSame(oldItem: PreviousTransactionRecords, newItem: PreviousTransactionRecords): Boolean {
//                return oldItem.custName == newItem.custName
//            }
//        }
//    }
//}