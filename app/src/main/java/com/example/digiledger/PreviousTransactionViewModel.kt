package com.example.digiledger

import android.app.Application
import androidx.lifecycle.*
import com.example.digiledger.data.PreviousTransactionRecords
import com.example.digiledger.data.PreviousTransactionRepository
import com.example.digiledger.data.PreviousTransactionsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreviousTransactionViewModel(application: Application) : AndroidViewModel(application) {

    val getAll: LiveData<List<PreviousTransactionRecords>>
    private val repository: PreviousTransactionRepository

    init {
        val previousTransactionRecordsDao =
            PreviousTransactionsDatabase.getDatabase(application).previousTransactionDao()
        repository = PreviousTransactionRepository(previousTransactionRecordsDao)
        getAll = repository.getAll
    }

    fun addRecord(previousTransactionsRecords: PreviousTransactionRecords) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecord(previousTransactionsRecords)
        }
    }
}

