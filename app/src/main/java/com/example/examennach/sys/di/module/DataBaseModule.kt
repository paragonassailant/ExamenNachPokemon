package com.example.examennach.sys.di.module

import android.content.Context
import androidx.room.Room
import com.example.examennach.data.datasource.db.declaration.AppDataBase
import com.example.examennach.sys.util.Constants.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
            .build()
    }
}