package com.fincare.animeseeker.model

data class AnimeRes(
    val data: List<Data>
)

data class Data(
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val rating: String,
    val episodes: Int,
    val score: Float,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val images: AnimeImage

)

data class AnimeImage(
    val jpg: ImageUrl
)

data class ImageUrl(
    val image_url: String
)

data class AnimeDetailRes(
    val title: String,
    val title_english: String,
    val title_synonyms: String,
    val synopsis: String,
    val url: String,
    val episodes: Int,
    val score: Float,
    val rating: String,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val images: AnimeImage
)

data class Genre(
    val name: String
)

data class Trailer(
    val url: String,
    val youtube_id: String
)
