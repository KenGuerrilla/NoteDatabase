package com.itl.kglab.notedatabase.db

import androidx.room.Delete
import androidx.room.Query

interface NoteDao {

    @Query("SELECT * FROM noteData")
    fun getAll(): List<NoteData>

    @Query("SELECT * FROM noteData WHERE id = :id")
    fun getNoteById(id: Int): NoteData

    @Delete
    fun delete(note: NoteData)
}