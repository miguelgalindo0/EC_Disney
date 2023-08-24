package com.miguel.disney.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Personaje (
    val _id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String,
    val imageUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val alignment: String,
    val parkAttractions: List<String>,
    val allies: List<String>,
    val enemies: List<String>,
    var isFavorite: Boolean = false
) : Parcelable

fun Personaje.toPersonajeEntity():PersonajeEntity {
    val filmsString = this.films.joinToString("|")
    val shortFilmsString = this.shortFilms.joinToString("|")
    val tvShowsString = this.tvShows.joinToString("|")
    val videoGamesString = this.videoGames.joinToString("|")
    val parkAttractionsString = this.parkAttractions.joinToString("|")
    val alliesString = this.allies.joinToString("|")
    val enemiesString = this.enemies.joinToString("|")
    return PersonajeEntity(
        _id, url, name, sourceUrl, imageUrl, filmsString, shortFilmsString, tvShowsString, videoGamesString, alignment, parkAttractionsString, alliesString, enemiesString, isFavorite
    )
}

fun getData() : List<Personaje> {
    return listOf(
//        Personaje(1,
//            "https://disneyapi/character/24",
//            "Buzz",
//            "https://disney.fandom.com/wiki/%27Olu_Mel",
//            "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png",
//            listOf("Toy Story","Toy Story 2"),
//            listOf("Toy Story","Toy Story 2"),
//            listOf("Toy Story","Toy Story 2"),
//            listOf("Toy Story","Toy Story 2"),
//            "center",
//            listOf("Toy Story","Toy Story 2"),
//            listOf("Toy Story","Toy Story 2"),
//            listOf("Toy Story","Toy Story 2"),
//            false
//        )
    )
}