package com.fincare.animeseeker.uiscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fincare.animeseeker.databinding.ItemAnimeBinding
import com.fincare.animeseeker.model.Data

class AnimeAdapter(
    private var animeList: List<Data>,
    private val itemClickListener: (Data) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(anime: Data) {
            binding.title.text = anime.title
            binding.episodes.text = "Episodes number: ${anime.episodes}"
            binding.rating.text = "Rating: ${anime.score}"
            Glide.with(binding.poster.context)
                .load(anime.images.jpg.image_url)
                .into(binding.poster)

            binding.postLayout.setOnClickListener {
                itemClickListener(anime)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    fun updateData(newList: List<Data>) {
        animeList = newList
        notifyDataSetChanged()
    }
}
