package com.itl.kglab.notedatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.itl.kglab.notedatabase.db.AppDatabase
import com.itl.kglab.notedatabase.db.NoteData
import com.itl.kglab.notedatabase.repository.NoteRepository
import com.itl.kglab.notedatabase.ui.state.EditNoteViewState
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<EditNoteViewState>()
    val viewStateLiveData: LiveData<EditNoteViewState> = _viewStateLiveData

    private var noteData: NoteData = NoteData(-1, "", "", "")

    fun updateNote(
        title: String,
        date: String,
        note: String
    ) {
        noteData = noteData.copy(
            title = title,
            date = date,
            note = note
        )
    }

    fun getNoteDataById(id: Int) {
        _viewStateLiveData.value = EditNoteViewState.Loading
        viewModelScope.launch {
            val data = repository.getNoteById(id) ?: NoteData(-1, "", "", "")
            noteData = data
            _viewStateLiveData.value = EditNoteViewState.InitView(data)
        }
    }

    fun saveNote() {
        _viewStateLiveData.value = EditNoteViewState.Loading
        viewModelScope.launch {
            if (noteData.id == -1) {
                repository.addNote(noteData)
            } else {
                repository.editNote(noteData)
            }
            _viewStateLiveData.value = EditNoteViewState.Confirm
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[APPLICATION_KEY])
                val db = AppDatabase.invoke(application)
                val repository = NoteRepository(db)

                return EditNoteViewModel(
                    repository = repository
                ) as T
            }
        }

    }
}