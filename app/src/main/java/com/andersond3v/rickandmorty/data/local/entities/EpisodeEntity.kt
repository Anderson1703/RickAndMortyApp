package com.andersond3v.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=0,
    val air_date: String,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
)
