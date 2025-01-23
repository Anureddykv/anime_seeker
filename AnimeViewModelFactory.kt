package com.fincare.animeseeker.uiscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fincare.animeseeker.repository.ApiRepository
import com.fincare.animeseeker.viewmodel.AnimeViewModel

class AnimeViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            return AnimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
