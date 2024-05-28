package com.andersond3v.rickandmorty.data.remote.repository

import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.data.remote.RickAndMortyService

class RickAndMortyRepository(
    private val rickAndMortyService: RickAndMortyService
) {
     suspend fun getCharacters(page: Int): Result<List<Character>>  {
        return try {
             Result.success(rickAndMortyService.getCharacters(page).results)
        }catch (e:Exception){
            return Result.failure(e)
        }
    }

    suspend fun getEpisodes(page: Int): Result<List<Episode>> {
        return try {
            Result.success(rickAndMortyService.getEpisodes(page).results)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    suspend fun getCharacter(id: Int): Result<Character> {
        return try {
            Result.success(rickAndMortyService.getCharacter(id))
        } catch (e:Exception){
            Result.failure(e)
        }
    }

    suspend fun getEpisode(id: Int): Result<Episode> {
        return try {
            Result.success(rickAndMortyService.getEpisode(id))
        } catch (e:Exception){
            Result.failure(e)
        }
    }
}