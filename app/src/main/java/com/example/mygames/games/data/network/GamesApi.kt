package com.example.mygames.games.data.network

import com.example.mygames.games.data.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Prashant Rane on 04-03-2022.
 */
interface GamesApi {

    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteList>
}