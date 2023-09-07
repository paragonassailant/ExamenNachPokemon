package com.example.examennach.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.domain.PokemonRepository
import com.example.examennach.sys.util.ErrorObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(private var pokemonRepository: PokemonRepository) :
    ViewModel() {

    val onSuccess: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val onError by lazy { MutableLiveData<ErrorObserver>() }


    fun requestPokemon() {
        pokemonRepository.requestPokemon(buildRequest(), buildError())
    }

    private fun buildRequest(): Observer<List<Pokemon>> {
        return Observer {
            if (it.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    onSuccess.postValue(it)
                }
            }
        }
    }

    private fun buildError(): Observer<ErrorObserver> {
        return Observer {
            onError.postValue(it)
        }
    }
}