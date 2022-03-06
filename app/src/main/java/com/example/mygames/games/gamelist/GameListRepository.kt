package com.example.mygames.games.gamelist

import android.util.Log
import com.example.mygames.games.data.model.Game
import com.example.mygames.games.data.network.GamesApi
import javax.inject.Inject

/**
 * Created by Prashant Rane on 04-03-2022.
 */
class GameListRepository @Inject constructor(var gameApi: GamesApi) {

/*    suspend fun getList(): Flow<List<ResultModel>> {
        return flow {
            val responseList = gameApi.getQuotes()
            if (responseList.isSuccessful) {
                responseList.body()?.resultModels?.let {
                    emit(it.map {
                        ResultModel(_id = it._id, author = it.author)
                    })
                }
            } else {
                throw Exception(responseList.errorBody().toString())
            }
        }
    }*/

    suspend fun getGamesList(): List<Game>? {
        val responseList = gameApi.getGamesList()
        return if (responseList.isSuccessful) {
            Log.d("Prashant", responseList.body()?.size.toString())
            responseList.body() ?: ArrayList()
        } else {
            ArrayList()
        }
    }

}