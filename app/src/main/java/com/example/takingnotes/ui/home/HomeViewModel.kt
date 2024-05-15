package com.example.takingnotes.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.takingnotes.data.Note
import com.example.takingnotes.data.NoteDatabase
import com.example.takingnotes.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun add(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.add(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}