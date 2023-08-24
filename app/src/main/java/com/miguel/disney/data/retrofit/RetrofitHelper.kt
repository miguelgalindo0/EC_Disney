package com.miguel.disney.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val personajeService: PersonajeService = retrofit.create(PersonajeService::class.java)
}