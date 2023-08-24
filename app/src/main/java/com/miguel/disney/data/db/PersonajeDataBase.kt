package com.miguel.disney.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.miguel.disney.model.Personaje
import com.miguel.disney.model.PersonajeEntity

@Database(entities = [PersonajeEntity::class], version = 3) // Aumentar la versión a 2
abstract class PersonajeDataBase : RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao

    companion object {
        @Volatile
        private var instance: PersonajeDataBase? = null

        fun getDatabase(context: Context): PersonajeDataBase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val migration1to2 = Migration1to2() // Definir la migración
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonajeDataBase::class.java,
                    "personajeDB"
                )
                    .addMigrations(migration1to2) // Agregar la migración a la instancia
                    .build()
                instance = _instance
                return _instance
            }
        }
    }

    private class Migration1to2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Agregar la nueva columna "imageUrl" a la tabla "personaje"
            database.execSQL("ALTER TABLE personaje ADD COLUMN imageUrl TEXT")
        }
    }
}