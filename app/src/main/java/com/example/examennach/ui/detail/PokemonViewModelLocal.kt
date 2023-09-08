package com.example.examennach.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.domain.PokemonLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModelLocal @Inject constructor(private val pokemonLocalRepository: PokemonLocalRepository):ViewModel() {

    var onSuccess: MutableLiveData<Pokemon> = MutableLiveData()
    fun requestDetails(id: Int) {
        pokemonLocalRepository.getDetailPokemon(id, pokemonDetail())
    }

    private fun pokemonDetail(): Observer<Pokemon> {
        return Observer {
            onSuccess.postValue(it)
        }
    }
}