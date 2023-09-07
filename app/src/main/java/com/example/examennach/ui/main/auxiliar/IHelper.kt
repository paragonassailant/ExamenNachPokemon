package com.example.examennach.ui.main.auxiliar

import com.example.examennach.data.entities.Pokemon

interface IHelper {

    fun pokemon(pokemon: Pokemon)

    fun favorite(position: Int, isChecked: Boolean)
}