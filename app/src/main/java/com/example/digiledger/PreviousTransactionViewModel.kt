package com.example.digiledger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.data.PreviousTransactionRecordsDao
import com.example.digiledger.data.PreviousTransactionsDatabase
import java.lang.IllegalArgumentException
import kotlinx.coroutines.launch



class PreviousTransactionViewModel(private val previousTransactionRecordsDao: PreviousTransactionRecordsDao): ViewModel() {

    private fun addRecords(previousTransactionRecords: PreviousTransactionRecords){
        viewModelScope.launch {
            previousTransactionRecordsDao.insert(previousTransactionRecords)
        }
    }

    private fun getNewRecord(custName: String, billAmount: Double): PreviousTransactionRecords{
        return PreviousTransactionRecords(
            custName = custName,
            billAmount = billAmount
        )
    }

    fun addNewRecord(custName: String, billAmount: Double){
        val newRecord = getNewRecord(custName,billAmount)
        addRecords(newRecord)
    }
}

class PreviousTransactionFactory(private val previousTransactionRecordsDao: PreviousTransactionRecordsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviousTransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PreviousTransactionViewModel(previousTransactionRecordsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
