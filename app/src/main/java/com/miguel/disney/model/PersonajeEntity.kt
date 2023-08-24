package com.miguel.disney.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personaje")
data class PersonajeEntity (
    @PrimaryKey
    val _id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String,
    val imageUrl: String,
    val films: String,
    val shortFilms: String,
    val tvShows: String,
    val videoGames: String,
    val alignment: String,
    val parkAttractions: String,
    val allies: String,
    val enemies: String,
    var isFavorite: Boolean = false
){}

fun PersonajeEntity.toPersonaje(): Personaje {
    return Personaje(
        _id, url, name, sourceUrl, imageUrl, films.split("|"), shortFilms.split("|"), tvShows.split("|"), videoGames.split("|"), alignment, parkAttractions.split("|"), allies.split("|"), enemies.split("|"), isFavorite
    )
}