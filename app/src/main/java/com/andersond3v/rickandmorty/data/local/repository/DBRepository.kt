package com.andersond3v.rickandmorty.data.local.repository

import com.andersond3v.rickandmorty.data.local.RickAndMortyDao
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity
import kotlinx.coroutines.flow.Flow

class DBRepository(
    private val db: RickAndMortyDao
) {
     fun getCharacters():Flow<List<CharacterEntity>> = db.getCharacters()
    suspend fun insertCharacters(character:CharacterEntity) = db.insertCharacter(character)
     fun getEpisodes():Flow<List<EpisodeEntity>> = db.getEpisodes()
    suspend fun insertEpisodes(episode:EpisodeEntity) = db.insertEpisode(episode)
    suspend fun deleteCharacters(character: CharacterEntity) = db.deleteCharacters(character)
    suspend fun deleteEpisodes(episode: EpisodeEntity) = db.deleteEpisodes(episode)
}