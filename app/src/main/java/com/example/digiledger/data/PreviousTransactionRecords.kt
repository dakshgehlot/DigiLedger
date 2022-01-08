package com.example.digiledger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "transaction_history")
data class PreviousTransactionRecords (     // item class
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val custName: String,
    @ColumnInfo(name = "bill_amount")
    val billAmount: Int,
)

fun PreviousTransactionRecords.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(billAmount)