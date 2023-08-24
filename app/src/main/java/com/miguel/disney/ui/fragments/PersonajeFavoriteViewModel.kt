package com.miguel.disney.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miguel.disney.data.db.PersonajeDataBase
import com.miguel.disney.data.repository.PersonajeRepository
import com.miguel.disney.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeFavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : PersonajeRepository
    private var _favorites: MutableLiveData<List<Personaje>> = MutableLiveData()

    var favorites: LiveData<List<Personaje>> = _favorites

    init {
        val db = PersonajeDataBase.getDatabase(application)
        repository = PersonajeRepository(db)
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}