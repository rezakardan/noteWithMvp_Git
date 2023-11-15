package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.utils.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity (

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String="",
    var category:String="",
    var desc:String="",
    var priority:String=""



        )