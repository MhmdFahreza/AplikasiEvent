package com.example.aplikasievent.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var name: String = "",
    var mediaCover: String? = null,
)
