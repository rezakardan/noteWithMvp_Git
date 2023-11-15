package com.example.noteapp.ui.main

import com.example.noteapp.base.BasePresenter
import com.example.noteapp.data.model.NoteEntity

interface MainContract {


    interface View {


        fun showEmptyList()

        fun showAllNotes(notes: List<NoteEntity>)

        fun deleteMessage()


    }

    interface Presenter : BasePresenter {

        fun loadAllNotes()


        fun deleteNote(entity: NoteEntity)

        fun filterNoteByPriority(priority: String)


        fun searchNotes(searching:String)


    }


}