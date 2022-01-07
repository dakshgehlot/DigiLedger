package com.example.digiledger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_history")
data class PreviousTransactionRecords (     // item class
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val custName: String,
    @ColumnInfo(name = "bill_amount")
    val billAmount: Double,
)