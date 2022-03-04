package com.example.mygames.games.gamelist

import com.example.mygames.games.data.network.GamesApi
import com.example.mygames.games.domain.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Prashant Rane on 04-03-2022.
 */
class GameListRepository @Inject constructor(var gameApi: GamesApi) {

    suspend fun getList(): Flow<List<ResultModel>> {
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
    }

    suspend fun getListFromNetwork(): List<ResultModel> {
        val responseList = gameApi.getQuotes()
        return if (responseList.isSuccessful) {
            responseList.body()?.resultModels?.let {
                (it.map {
                    ResultModel(_id = it._id, author = it.author)
                })
            } ?: throw Exception("No data")
        } else {
            throw Exception(responseList.errorBody().toString())
        }
    }

}