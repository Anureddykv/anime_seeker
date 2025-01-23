# Anime Seeker
Welcome to Anime Explorer - a delightful Android app that lets you explore and discover anime series with ease! 🎉

## 🎯 Objective
Build an Android app using the Jikan API to fetch and display a list of anime series, allowing users to view details and trailers.

## 📋 Requirements

### 1. Anime List Page 🏠
- Fetch a list of popular or top-rated anime using the Jikan API.
- Display the following information on the Home screen:
  - **Title**
  - **Number of Episodes**
  - **Rating** (e.g., MyAnimeList score)
  - **Poster Image**

### 2. Anime Detail Page 📺
- When an anime is clicked, navigate to the Anime Detail Page displaying:
  - **Video Player** for the trailer if available, otherwise the poster image.
  - **Title**
  - **Plot/Synopsis**
  - **Genre(s)**
  - **Main Cast**
  - **Number of Episodes**
  - **Rating**

## 🌐 API Endpoints
- **Top Anime:** \`https://api.jikan.moe/v4/top/anime\`
- **Anime Details:** \`https://api.jikan.moe/v4/anime/{anime_id}\`

## 🚀 How to Run
1. Clone the repository: \`git clone <repository-link>\`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.
