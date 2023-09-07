package com.example.examennach.sys.di.module

import com.example.examennach.data.datasource.db.dao.PokemonDao
import com.example.examennach.data.datasource.db.declaration.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [DataBaseModule::class])
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    //DISK DS O DAO
    @Provides
    @Singleton
    fun providePokemon(appDataBase: AppDataBase): PokemonDao {
        return appDataBase.getPokemon()
    }

}