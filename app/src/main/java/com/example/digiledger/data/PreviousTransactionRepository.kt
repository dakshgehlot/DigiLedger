package com.example.digiledger.data

import androidx.lifecycle.LiveData

class PreviousTransactionRepository(private val previousTransactionRecordsDao: PreviousTransactionRecordsDao) {

    val getAll: LiveData<List<PreviousTransactionRecords>> = previousTransactionRecordsDao.getAll()

    suspend fun addRecord(previousTransactionRecords: PreviousTransactionRecords){
        previousTransactionRecordsDao.insert(previousTransactionRecords)
    }

}