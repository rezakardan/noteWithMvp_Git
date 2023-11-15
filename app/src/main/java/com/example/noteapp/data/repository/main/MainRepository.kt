package com.example.noteapp.data.repository.main

import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.room.NoteDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao:NoteDao) {

    fun getAllNotes()=dao.getAllNotes()


    fun deleteNote(entity: NoteEntity)=dao.deleteNote(entity)

fun filterByPriority(priority:String)=dao.filterByPriority(priority)


    fun searchNotes(searching:String)=dao.searchNotes(searching)
}