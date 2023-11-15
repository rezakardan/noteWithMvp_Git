package com.example.noteapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDao():NoteDao

}