package com.example.examennach.data.datasource.local

import com.example.examennach.data.datasource.db.dao.PokemonDao
import com.example.examennach.data.entities.Pokemon
import javax.inject.Inject

class PokemonLocal @Inject constructor(private val pokemonDao: PokemonDao) {

    fun getDetail(id: Int): Pokemon {
        return pokemonDao.getDetail(id)
    }
}