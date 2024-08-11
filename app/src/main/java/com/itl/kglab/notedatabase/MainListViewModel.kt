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
import com.itl.kglab.notedatabase.ui.state.MainListViewState
import kotlinx.coroutines.launch

class MainListViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<MainListViewState>()
    val viewStateLiveData: LiveData<MainListViewState> = _viewStateLiveData

    fun getNoteList() {
        _viewStateLiveData.value = MainListViewState.Loading
        viewModelScope.launch {
            val noteList = repository.getNoteList()
            _viewStateLiveData.value = MainListViewState.UpdateView(noteList)
        }
    }

    fun addNote() {
        _viewStateLiveData.value = MainListViewState.Loading
        viewModelScope.launch {
            repository.addNote(
                NoteData(id = 0, title = "test", date = "2024/08/11", note = "This is a test note")
            )
            getNoteList()
        }
    }

    fun deleteNoteById(id: Int) {
        _viewStateLiveData.value = MainListViewState.Loading
        viewModelScope.launch {
            repository.deleteNoteById(id)
            val noteList = repository.getNoteList()
            _viewStateLiveData.value = MainListViewState.UpdateView(noteList)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[APPLICATION_KEY])
                val db = AppDatabase.invoke(application)
                val repository = NoteRepository(db)

                return MainListViewModel(
                    repository = repository
                ) as T
            }
        }
    }

}