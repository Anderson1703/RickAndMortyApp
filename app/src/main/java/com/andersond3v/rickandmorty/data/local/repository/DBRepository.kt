package com.andersond3v.rickandmorty.data.local.repository

import com.andersond3v.rickandmorty.data.local.DatabaseService
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity
import kotlinx.coroutines.flow.Flow

class DBRepository(
    private val db: DatabaseService
) {
    suspend fun getCharacters():Flow<List<CharacterEntity>> = db.dao().getCharacters()
    suspend fun insertCharacters(character:CharacterEntity) = db.dao().insertCharacter(character)
    suspend fun getEpisodes():Flow<List<EpisodeEntity>> = db.dao().getEpisodes()
    suspend fun insertEpisodes(episode:EpisodeEntity) = db.dao().insertEpisode(episode)
    suspend fun deleteCharacters(character: CharacterEntity) = db.dao().deleteCharacters(character)
    suspend fun deleteEpisodes(episode: EpisodeEntity) = db.dao().deleteEpisodes(episode)
}