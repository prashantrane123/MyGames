package com.example.mygames.games.data.model

import com.example.kotlin_retro_coroutine.data.ResultModel

/**
 * Created by Prashant Rane on 04-03-2022.
 */
data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val resultModels: List<ResultModel>,
    val totalCount: Int,
    val totalPages: Int
)