package com.example.mysampleonlineapplication.data.network.tesla

data class TeslaDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)