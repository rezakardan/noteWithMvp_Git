package com.example.noteapp.room

import androidx.room.*
import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.utils.NOTE_TABLE
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveNote(entity:NoteEntity):Completable

   @Delete
    fun deleteNote(entity: NoteEntity):Completable

   @Update
   fun updateNote(entity: NoteEntity):Completable




   @Query( "SELECT * FROM $NOTE_TABLE")
    fun getAllNotes():Observable<List<NoteEntity>>


    @Query("SELECT * FROM $NOTE_TABLE WHERE id ==:id ")
    fun getOneNotesById(id:Int):Observable<NoteEntity>

   @Query("SELECT*FROM $NOTE_TABLE WHERE priority == :priority")
    fun filterByPriority(priority:String):Observable<List<NoteEntity>>

@Query("SELECT*FROM $NOTE_TABLE WHERE title LIKE '%'||:searching || '%'")
    fun searchNotes(searching:String):Observable<List<NoteEntity>>

}