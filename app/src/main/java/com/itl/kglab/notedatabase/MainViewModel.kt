package com.itl.kglab.notedatabase

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getNoteList(): List<NoteData> {
        return mutableListOf<NoteData>().apply {
            repeat(10) { index ->
                add(
                    NoteData("Note Title $index", "2023-01-01", "Note Content $index")
                )
            }
        }
    }

}