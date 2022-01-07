package com.example.digiledger

import android.app.Application
import com.example.digiledger.data.PreviousTransactionsDatabase

class TransactionDatabaseInstance: Application() {
    val database: PreviousTransactionsDatabase by lazy{
        PreviousTransactionsDatabase.getDatabase(
            this
        )
    }
}