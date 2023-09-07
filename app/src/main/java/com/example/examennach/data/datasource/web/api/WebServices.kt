package com.example.examennach.data.datasource.web.api

import com.example.examennach.data.datasource.web.response.OnPokemonResponse
import com.example.examennach.sys.util.Constants.Companion.COMPLEMENT
import com.example.examennach.sys.util.Constants.Companion.URL_BASE
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("$URL_BASE$COMPLEMENT")
    fun getPokemon(): Call<OnPokemonResponse>
}