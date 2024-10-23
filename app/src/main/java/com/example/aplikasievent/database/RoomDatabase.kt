package com.example.aplikasievent.database

import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactsContract.CommonDataKinds.Note::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {

}