package com.miguel.disney.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miguel.disney.model.PersonajeEntity

@Dao
interface PersonajeDao {

    @Insert
    suspend fun addPersonajeToFavorite(personaje: PersonajeEntity)

    @Query("select * from personaje")
    suspend fun getFavorites() : List<PersonajeEntity>
}