package com.fincare.animeseeker.uiscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fincare.animeseeker.R
import com.fincare.animeseeker.api.RetrofitInstance
import com.fincare.animeseeker.databinding.ActivityMainBinding
import com.fincare.animeseeker.model.Data
import com.fincare.animeseeker.repository.ApiRepository
import com.fincare.animeseeker.viewmodel.AnimeViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: AnimeViewModel by viewModels {
        AnimeViewModelFactory(ApiRepository(RetrofitInstance.apiService))
    }

    private val animeAdapter = AnimeAdapter(emptyList()) { anime ->
        onAnimeItemClicked(anime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTopAnime()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = animeAdapter
    }

    private fun observeViewModel() {
        viewModel.animeList.observe(this) { response ->
            response?.data?.let {
                animeAdapter.updateData(it)
            } ?: run {
                Log.e("MainActivity", "No data found or failed to fetch data")
            }
        }

    }

    private fun onAnimeItemClicked(anime: Data) {
        Log.d("MainActivity", "Clicked Anime ID: ${anime.mal_id}")
        viewModel.fetchAnimeDetails(anime.mal_id)
        val fragment = AnimeDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("animeId", anime.mal_id)
                putString("title", anime.title)
                putString("synopsis", anime.synopsis)
                putInt("episodes", anime.episodes)
                putString("rating", anime.rating)
                putStringArrayList("genres", ArrayList(anime.genres.map { it.name }))
                putString("imageUrl", anime.images.jpg.image_url)
                anime.trailer?.let {
                    putString("videoUrl", it.youtube_id)
                }
            }
        }
        binding.recyclerView.visibility = View.GONE
        binding.fragmentContainer.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.recyclerView.visibility = View.VISIBLE
        binding.fragmentContainer.visibility = View.GONE
    }
}