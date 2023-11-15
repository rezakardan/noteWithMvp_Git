package com.example.noteapp.data.repository.add

import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.room.NoteDao
import com.example.noteapp.room.NoteDatabase
import javax.inject.Inject

class AddNoteRepository @Inject constructor(private val noteDao:NoteDao) {

    fun saveNote(entity: NoteEntity)=noteDao.saveNote(entity)


    fun detailNote(id: Int) = noteDao.getOneNotesById(id)

    fun updateNote(entity:NoteEntity)=noteDao.updateNote(entity)



}