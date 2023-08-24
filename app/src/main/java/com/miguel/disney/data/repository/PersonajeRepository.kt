package com.miguel.disney.data.repository

import com.miguel.disney.data.PersonajeServiceResult
import com.miguel.disney.data.db.PersonajeDao
import com.miguel.disney.data.db.PersonajeDataBase
import com.miguel.disney.data.response.ListPersonajeResponse
import com.miguel.disney.data.retrofit.RetrofitHelper
import com.miguel.disney.model.Personaje
import com.miguel.disney.model.toPersonaje
import com.miguel.disney.model.toPersonajeEntity

class PersonajeRepository(val db: PersonajeDataBase? = null) {

    private val dao: PersonajeDao? = db?.personajeDao()


    suspend fun getPersonajes(): PersonajeServiceResult<ListPersonajeResponse> {
        return try {
            val response = RetrofitHelper.personajeService.getAllPersonajes()
            PersonajeServiceResult.Success(response)
        } catch (e:Exception){
            PersonajeServiceResult.Error(e)
        }

    }

    suspend fun getFavorites() : List<Personaje> {
        dao?.let {
            val data = dao.getFavorites()
            var personajes: MutableList<Personaje> = mutableListOf()
            for (personajeEntity in data) {
                personajes.add(personajeEntity.toPersonaje())
            }
            return personajes
        } ?: kotlin.run {
            return listOf()
        }
    }

    suspend fun addPersonajeFavorites(personaje: Personaje) {
        dao?.let {
            dao.addPersonajeToFavorite(personaje.toPersonajeEntity())
        }
    }
}