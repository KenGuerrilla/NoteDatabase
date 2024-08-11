package com.itl.kglab.notedatabase.repository

import com.itl.kglab.notedatabase.db.AppDatabase
import com.itl.kglab.notedatabase.db.NoteData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(
    private val database: AppDatabase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addNote(noteData: NoteData) = withContext(coroutineDispatcher) {
        database.noteDao().addNote(noteData)
    }

    suspend fun editNote(noteData: NoteData) = withContext(coroutineDispatcher) {
        database.noteDao().editNote(noteData)
    }

    suspend fun deleteNoteById(id: Int) = withContext(coroutineDispatcher) {
        database.noteDao().deleteById(id)
    }

    suspend fun getNoteList(): List<NoteData> = withContext(coroutineDispatcher) {
        database.noteDao().getAll()
    }

    suspend fun getNoteById(id: Int): NoteData? = withContext(coroutineDispatcher) {
        database.noteDao().getNoteById(id)
    }
}