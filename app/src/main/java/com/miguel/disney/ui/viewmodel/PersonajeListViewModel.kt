package com.miguel.disney.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.disney.data.PersonajeServiceResult
import com.miguel.disney.data.repository.PersonajeRepository
import com.miguel.disney.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeListViewModel: ViewModel() {
    private val _personajes: MutableLiveData<List<Personaje>> = MutableLiveData<List<Personaje>>()
    val notes: LiveData<List<Personaje>> = _personajes
    private val repository = PersonajeRepository()

//    fun getAllPersonajes() {
//        val personajesList = getData()
//        _personajes.value = personajeList
//    }

    fun getPersonajesFromService() {
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getPersonajes()
            when(response) {
                is PersonajeServiceResult.Success -> {
                    _personajes.postValue(response.data.personajes)
                }
                is PersonajeServiceResult.Error -> {

                }
            }
        }
    }
}