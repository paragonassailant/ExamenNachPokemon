package com.example.examennach.data.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examennach.data.entities.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM Pokemon ")
    fun getPokemon():List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE id =:id")
    fun getDetail(id:Int):Pokemon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(a:Pokemon)

    @Query("DELETE FROM Pokemon")
    fun truncate()
}