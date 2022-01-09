package com.example.digiledger.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PreviousTransactionRecords::class], version = 1, exportSchema = false)
abstract class PreviousTransactionsDatabase : RoomDatabase() {

    abstract fun previousTransactionDao(): PreviousTransactionRecordsDao

    companion object {
        @Volatile
        private var INSTANCE: PreviousTransactionsDatabase? = null
        fun getDatabase(context: Context): PreviousTransactionsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PreviousTransactionsDatabase::class.java,
                    "digiledger_records"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}