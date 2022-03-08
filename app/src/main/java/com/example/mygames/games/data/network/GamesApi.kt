package com.example.mygames.games.data.network

import com.example.mygames.games.data.model.GameList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Prashant Rane on 04-03-2022.
 */
interface GamesApi {

    /*Query parameters can be used here */
    @GET("api/1.0/games?title=batman&limit=60&exact=0")
    suspend fun getGamesList(): Response<GameList>
}