package com.itl.kglab.notedatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteData")
    fun getAll(): List<NoteData>

    @Query("SELECT * FROM noteData WHERE id = :id")
    fun getNoteById(id: Int): NoteData

    @Insert
    fun addNote(note: NoteData)

    @Delete
    fun delete(note: NoteData)
}