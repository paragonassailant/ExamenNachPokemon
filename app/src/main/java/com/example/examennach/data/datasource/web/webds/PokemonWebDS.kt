package com.example.examennach.data.datasource.web.webds

import androidx.lifecycle.Observer
import com.example.examennach.data.datasource.web.api.WebServices
import com.example.examennach.data.datasource.web.response.OnPokemonResponse
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.sys.util.Constants.Companion.RETROFIT_FAILURE
import com.example.examennach.sys.util.ErrorObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PokemonWebDS @Inject constructor(private val webServices: WebServices) {

    fun getPokemon(observer: Observer<List<Pokemon>>, error: Observer<ErrorObserver>) {
        webServices.getPokemon().enqueue(object :Callback<OnPokemonResponse>{
            override fun onResponse(call: Call<OnPokemonResponse>, response: Response<OnPokemonResponse>) {
                CoroutineScope(Dispatchers.IO).launch {
                    when(response.code()){
                        200 ->{
                            if (response.body()?.results != null){
                                observer.onChanged(response.body()!!.results)
                            }else{
                                error.onChanged(ErrorObserver(RETROFIT_FAILURE, response.message()))
                            }
                        }
                        400 ->{
                            error.onChanged(ErrorObserver(RETROFIT_FAILURE, response.message()))
                        }
                        404->{
                            error.onChanged(ErrorObserver(RETROFIT_FAILURE, response.message()))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OnPokemonResponse>, t: Throwable) {
                error.onChanged(ErrorObserver(RETROFIT_FAILURE, t.message))
            }

        })
    }
}