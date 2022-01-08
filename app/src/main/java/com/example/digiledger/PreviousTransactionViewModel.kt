package com.example.digiledger

import android.app.Application
import androidx.lifecycle.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.data.PreviousTransactionRecordsDao
import com.example.digiledger.data.PreviousTransactionRepository
import com.example.digiledger.data.PreviousTransactionsDatabase
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException
import kotlinx.coroutines.launch

class PreviousTransactionViewModel (application: Application): AndroidViewModel(application){

    val getAll: LiveData<List<PreviousTransactionRecords>>
    private val repository: PreviousTransactionRepository

    init {
        val previousTransactionRecordsDao = PreviousTransactionsDatabase.getDatabase(application).previousTransactionDao()
        repository = PreviousTransactionRepository(previousTransactionRecordsDao)
        getAll = repository.getAll
    }

    fun addRecord(previousTransactionsRecords: PreviousTransactionRecords){
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.addRecord(previousTransactionsRecords)
        }
    }
}

//
//class PreviousTransactionViewModel(private val previousTransactionRecordsDao: PreviousTransactionRecordsDao): ViewModel() {
//
//    val allRecords: LiveData<List<PreviousTransactionRecords>> = previousTransactionRecordsDao.getAll().asLiveData()
//
//    private fun addRecords(previousTransactionRecords: PreviousTransactionRecords){
//        viewModelScope.launch {
//            previousTransactionRecordsDao.insert(previousTransactionRecords)
//        }
//    }
//
//    private fun getNewRecord(custName: String, billAmount: Int): PreviousTransactionRecords{
//        return PreviousTransactionRecords(
//            custName = custName,
//            billAmount = billAmount
//        )
//    }
//
//    fun addNewRecord(custName: String, billAmount: Int){
//        val newRecord = getNewRecord(custName,billAmount)
//        addRecords(newRecord)
//    }
//}
//
//class PreviousTransactionFactory(private val previousTransactionRecordsDao: PreviousTransactionRecordsDao) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PreviousTransactionViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return PreviousTransactionViewModel(previousTransactionRecordsDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
