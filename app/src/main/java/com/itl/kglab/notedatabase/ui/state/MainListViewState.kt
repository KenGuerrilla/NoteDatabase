package com.itl.kglab.notedatabase.ui.state

import com.itl.kglab.notedatabase.db.NoteData

sealed class MainListViewState {

    data object Loading : MainListViewState()
    data class UpdateView(val noteList: List<NoteData>) : MainListViewState()

}