package com.itl.kglab.notedatabase.ui.state

import com.itl.kglab.notedatabase.db.NoteData

sealed class EditNoteViewState {

    data object Loading : EditNoteViewState()
    data class InitView(val noteData: NoteData) : EditNoteViewState()
    data object Confirm : EditNoteViewState()

}