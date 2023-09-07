package com.example.examennach.domain

import android.content.Context
import androidx.lifecycle.Observer
import com.example.examennach.data.datasource.db.dao.PokemonDao
import com.example.examennach.data.datasource.web.webds.PokemonWebDS
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.sys.util.Connection
import com.example.examennach.sys.util.Constants.Companion.RETROFIT_FAILURE
import com.example.examennach.sys.util.ErrorObserver
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val webDS: PokemonWebDS,
    @ApplicationContext private var context: Context, private var pokemonDao: PokemonDao) {

    fun requestPokemon(observer: Observer<List<Pokemon>>, error: Observer<ErrorObserver>){
        if (Connection.connection(context)){
            webDS.getPokemon(buildRequest(observer,error),error)
        }else{
            getLocalData(observer)
        }
    }

    private fun buildRequest(observer: Observer<List<Pokemon>>, error: Observer<ErrorObserver>): Observer<List<Pokemon>> {
        return Observer{
            if (it.isEmpty()){
                error.onChanged(ErrorObserver(RETROFIT_FAILURE))
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    for (p:Pokemon in it){
                        pokemonDao.insert(p)
                    }
                    getLocalData(observer)
                }
            }
        }
    }

    private fun getLocalData(observer: Observer<List<Pokemon>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val pokemon = pokemonDao.getPokemon()
            observer.onChanged(pokemon)
        }
    }

}