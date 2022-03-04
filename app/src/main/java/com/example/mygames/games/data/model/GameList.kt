package com.example.mygames.games.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Prashant Rane on 04-03-2022.
 */
data class GameList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    @SerializedName("results")val games: List<Game>,
    val totalCount: Int,
    val totalPages: Int
)