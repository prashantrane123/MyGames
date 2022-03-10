package com.example.mygames.games.gamelist

import com.example.mygames.games.data.model.Game
import com.example.mygames.games.data.network.GamesApi
import javax.inject.Inject

/**
 * Created by Prashant Rane on 04-03-2022.
 */
class GameListRepository @Inject constructor(var gameApi: GamesApi) {

    suspend fun getGamesList(): List<Game>? {
        val responseList = gameApi.getGamesList()
        return if (responseList.isSuccessful) {
            responseList.body() ?: ArrayList()
        } else {
            ArrayList()
        }
    }

}