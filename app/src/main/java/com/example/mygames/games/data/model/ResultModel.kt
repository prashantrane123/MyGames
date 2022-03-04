package com.example.kotlin_retro_coroutine.data

/**
 * Created by Prashant Rane on 04-03-2022.
 */
data class ResultModel(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)