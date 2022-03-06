package com.example.mygames.di

import com.example.mygames.games.data.network.GamesApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Prashant Rane on 04-03-2022.
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGamesApiInstance():GamesApi {
        return Retrofit.Builder().baseUrl("https://www.cheapshark.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(GamesApi::class.java)
    }
}