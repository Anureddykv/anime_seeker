package com.fincare.animeseeker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fincare.animeseeker.model.AnimeDetailRes
import com.fincare.animeseeker.model.AnimeRes
import com.fincare.animeseeker.repository.ApiRepository
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: ApiRepository) : ViewModel() {

    private val animeResponseList = MutableLiveData<AnimeRes?>()
    private val animeListDetail = MutableLiveData<AnimeDetailRes?>()

    val animeList: LiveData<AnimeRes?> get() = animeResponseList
    val animeDetail: LiveData<AnimeDetailRes?> get() = animeListDetail



    fun fetchTopAnime() {
        viewModelScope.launch {
            val response = repository.getTopAnime()
            if (response != null) {
                if (response.data.isEmpty()) {
                    Log.e("AnimeViewModel", "No anime data available")
                } else {
                    animeResponseList.postValue(response)
                }
            } else {
                Log.e("AnimeViewModel", "Failed to fetch top anime")
            }
        }
    }

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetails(animeId)
                Log.d("AnimeViewModel", "Fetched anime details for ID: $animeId")
                animeListDetail.postValue(response)
            } catch (e: Exception) {
                animeListDetail.postValue(null)
                Log.e("AnimeViewModel", "Error fetching anime details: ${e.message}")
            }
        }
    }

}