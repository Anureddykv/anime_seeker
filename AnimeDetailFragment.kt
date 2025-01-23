package com.fincare.animeseeker.uiscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fincare.animeseeker.R
import com.fincare.animeseeker.api.RetrofitInstance
import com.fincare.animeseeker.databinding.FragmentAnimeDetailBinding
import com.fincare.animeseeker.repository.ApiRepository
import com.fincare.animeseeker.viewmodel.AnimeViewModel

class AnimeDetailFragment : Fragment() {

    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnimeViewModel by viewModels {
        AnimeViewModelFactory(ApiRepository(RetrofitInstance.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setImageResource(R.drawable.back)

        binding.back.setOnClickListener {

            activity?.onBackPressed();
        }
        val animeId = arguments?.getInt("animeId")
        val title = arguments?.getString("title")
        val synopsis = arguments?.getString("synopsis")
        val episodes = arguments?.getInt("episodes")
        val rating = arguments?.getString("rating", "N/A")
        val genres = arguments?.getStringArrayList("genres")
        val imageUrl = arguments?.getString("imageUrl")
        val videoUrl = arguments?.getString("videoUrl")

        animeId?.let {
            viewModel.fetchAnimeDetails(it)

            binding.title.text = title
            binding.rating.text = "Rating: $rating"
            binding.synopsis.text = synopsis
            binding.episodes.text = "Episodes: $episodes"
            binding.genres.text = "Genres: ${genres?.joinToString(", ")}"
            Glide.with(this).load(imageUrl).into(binding.posterImage)
                binding.posterImage.visibility = View.VISIBLE

            videoUrl?.let {
                val webView: WebView = binding.root.findViewById(R.id.webView)
                val iframeUrl = "https://www.youtube.com/embed/${extractYouTubeVideoId(videoUrl)}"
                Log.d("YouTube", "Iframe URL: $iframeUrl")
                webView.settings.javaScriptEnabled = true
                webView.settings.domStorageEnabled = true
                webView.settings.mediaPlaybackRequiresUserGesture = false
                webView.settings.allowUniversalAccessFromFileURLs = true
                webView.settings.allowFileAccessFromFileURLs = true
                webView.webViewClient = WebViewClient()
                webView.loadUrl(iframeUrl)
            }

        } ?: run {
        }
    }

    private fun extractYouTubeVideoId(url: String): String? {
        val pattern = "(?<=v=|embed\\/)([A-Za-z0-9_-]{11})"
        val regex = Regex(pattern)
        val match = regex.find(url)
        Log.d("YouTube", "Extracted Video ID: ${match?.groups?.get(1)?.value}")
        return match?.groups?.get(1)?.value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
