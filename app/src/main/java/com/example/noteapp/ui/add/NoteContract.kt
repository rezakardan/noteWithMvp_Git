package com.example.noteapp.ui.add

import com.example.noteapp.data.model.NoteEntity

interface NoteContract {


    interface View {


        fun close()

        fun loadNote(note: NoteEntity)





    }


    interface Presenter {


        fun saveNote(entity: NoteEntity)

        fun detailNote(id: Int)


        fun updateNote(entity: NoteEntity)

    }
}