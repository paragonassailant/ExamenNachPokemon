package com.example.examennach.data.datasource.web.response

import com.example.examennach.data.entities.Pokemon
import com.google.gson.annotations.SerializedName

data class OnPokemonResponse(
    @SerializedName("pokemon")  val results: List<Pokemon>
)