package com.example.aplikasievent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)

    @Query("SELECT * FROM EventEntity WHERE id = :id")
    suspend fun getEventById(id: String): EventEntity?

    @Query("SELECT * FROM EventEntity")
    fun getAllEvents(): LiveData<List<EventEntity>>
}
