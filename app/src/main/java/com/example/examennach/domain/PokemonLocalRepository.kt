package com.example.examennach.domain

import androidx.lifecycle.Observer
import com.example.examennach.data.datasource.local.PokemonLocal
import com.example.examennach.data.entities.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonLocalRepository @Inject constructor(private val pokemonLocal: PokemonLocal) {

    fun getDetailPokemon(id:Int,observer: Observer<Pokemon>){
        CoroutineScope(Dispatchers.IO).launch {
            observer.onChanged(pokemonLocal.getDetail(id))
        }
    }
}