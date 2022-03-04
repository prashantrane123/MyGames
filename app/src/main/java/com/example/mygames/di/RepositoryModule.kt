package com.example.mygames.di

import com.example.mygames.games.data.network.GamesApi
import com.example.mygames.games.gamelist.GameListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Prashant Rane on 04-03-2022.
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getGamesListRepositoryInstance(gameApi: GamesApi): GameListRepository {
        return GameListRepository(gameApi)
    }
}