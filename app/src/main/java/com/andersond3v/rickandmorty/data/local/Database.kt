package com.andersond3v.rickandmorty.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity

@Database(entities = [CharacterEntity::class, EpisodeEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase(){
    abstract fun dao(): Dao
    companion object{
        private var instance: DatabaseService? = null
        fun getInstance(context: Context): DatabaseService {
            return instance?: synchronized(this){
                Room.databaseBuilder(context, DatabaseService::class.java, "rick_and_morty_database")
                    .build()
                    .also { instance = it }
            }
        }
    }
}