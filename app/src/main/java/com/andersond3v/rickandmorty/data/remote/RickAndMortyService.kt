package com.andersond3v.rickandmorty.data.remote

import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.data.model.CharacterResponse
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.data.model.EpisodeResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    companion object {
        val instance: RickAndMortyService =
            Retrofit
                .Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
                .create(RickAndMortyService::class.java)
    }

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ):Character

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): EpisodeResponse

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): Episode

}