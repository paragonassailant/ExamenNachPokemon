package com.example.examennach.data.datasource.db.declaration

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examennach.data.datasource.db.dao.PokemonDao
import com.example.examennach.data.entities.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPokemon(): PokemonDao
}