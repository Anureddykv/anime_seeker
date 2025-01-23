package com.fincare.animeseeker.repository

import android.util.Log
import com.fincare.animeseeker.api.ApiService
import com.fincare.animeseeker.model.AnimeDetailRes
import com.fincare.animeseeker.model.AnimeRes



class ApiRepository(private val apiService: ApiService) {

    suspend fun getTopAnime(): AnimeRes? {
        try {
            val response = apiService.getTopAnime()
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("ApiRepository", "Error fetching top anime: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error fetching top anime: ${e.message}")
        }
        return null
    }


    suspend fun getAnimeDetails(animeId: Int): AnimeDetailRes? {
        try {
            val response = apiService.getAnimeDetails(animeId)
            if (response != null) {
                response
            } else {
                Log.e("ApiRepository", "Empty response from anime details API")
                null
            }
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error fetching anime details: ${e.message}")
            null
        }
        return null
    }


}
