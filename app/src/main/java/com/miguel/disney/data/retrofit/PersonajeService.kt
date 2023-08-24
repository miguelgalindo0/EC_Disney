package com.miguel.disney.data.retrofit

import com.miguel.disney.data.response.ListPersonajeResponse
import retrofit2.http.GET

interface PersonajeService {
    @GET("character")
    suspend fun getAllPersonajes(): ListPersonajeResponse
}
//https://api.disneyapi.dev/character