package com.example.musicapp.data.remote

import com.example.musicapp.data.remote.response.ChartResponse
import com.example.musicapp.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {
    @GET("chart")
    suspend fun getChart(): Response<ChartResponse>

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): Response<SearchResponse>
}