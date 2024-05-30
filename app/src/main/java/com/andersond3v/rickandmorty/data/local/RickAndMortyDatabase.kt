package com.andersond3v.rickandmorty.data.local

import androidx.room.RoomDatabase
import androidx.room.Database
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity

@Database(entities = [CharacterEntity::class, EpisodeEntity::class], version = 1)
abstract class RickAndMortyDatabase : RoomDatabase(){
    companion object{
        const val DATABASE_NAME = "rick_and_morty_db"
    }
    abstract fun dao(): RickAndMortyDao
}