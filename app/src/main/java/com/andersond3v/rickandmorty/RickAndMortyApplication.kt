package com.andersond3v.rickandmorty

import android.app.Application
import androidx.room.Room
import com.andersond3v.rickandmorty.data.local.RickAndMortyDatabase

class RickAndMortyApplication: Application() {
    companion object{
        lateinit var RickAndMortyDatabaseInstance: RickAndMortyDatabase
    }

    override fun onCreate() {
        super.onCreate()
        RickAndMortyDatabaseInstance = Room.databaseBuilder(
                applicationContext,
                RickAndMortyDatabase::class.java,
                RickAndMortyDatabase.DATABASE_NAME
            ).build()
    }
}