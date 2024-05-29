package com.andersond3v.rickandmorty.data.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM character_table")
    suspend fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM episode_table")
    suspend fun getEpisodes(): Flow<List<EpisodeEntity>>

    @Delete(entity = CharacterEntity::class)
    suspend fun deleteCharacters(character: CharacterEntity)

    @Delete(entity = EpisodeEntity::class)
    suspend fun deleteEpisodes(episode: EpisodeEntity)
}