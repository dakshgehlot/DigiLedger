package com.example.digiledger.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PreviousTransactionRecordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(previousTransactionRecords: PreviousTransactionRecords)

    @Query("SELECT * FROM transaction_history ORDER BY id ASC")
    fun getAll(): Flow<List<PreviousTransactionRecords>>
}