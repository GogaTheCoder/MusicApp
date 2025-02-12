package com.example.musicapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {
    @GET("chart")
    suspend fun getChart(): ChartResponse

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): SearchResponse
}