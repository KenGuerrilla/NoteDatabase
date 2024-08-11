package com.itl.kglab.notedatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteData")
    fun getAll(): List<NoteData>

    @Query("SELECT * FROM noteData WHERE id = :id")
    fun getNoteById(id: Int): NoteData?

    @Update
    fun editNote(note: NoteData)

    @Insert
    fun addNote(note: NoteData)

    @Delete
    fun delete(note: NoteData)
}