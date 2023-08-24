package com.miguel.disney.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.disney.data.db.PersonajeDataBase
import com.miguel.disney.data.repository.PersonajeRepository
import com.miguel.disney.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeDetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PersonajeRepository

    init {
        val db = PersonajeDataBase.getDatabase(application)
        repository = PersonajeRepository(db)
    }

    fun addFavorites(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPersonajeFavorites(personaje)
        }
    }

}