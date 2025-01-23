package com.fincare.animeseeker.api

import com.fincare.animeseeker.model.AnimeDetailRes
import com.fincare.animeseeker.model.AnimeRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): Response<AnimeRes>

    @GET("anime/{malId}")
    suspend fun getAnimeDetails(@Path("malId") malId: Int): AnimeDetailRes


}

