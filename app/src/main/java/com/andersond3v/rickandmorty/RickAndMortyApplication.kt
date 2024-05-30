package com.andersond3v.rickandmorty

import android.app.Application
import androidx.room.Room
import com.andersond3v.rickandmorty.data.local.DatabaseService

class RickAndMortyApplication: Application() {
    companion object{
        lateinit var RickAndMortyDatabase: DatabaseService
    }

    override fun onCreate() {
        super.onCreate()
        RickAndMortyDatabase = Room
            .databaseBuilder(
                applicationContext,
                DatabaseService::class.java,
                DatabaseService.DATABASE_NAME
            ).build()
    }

}