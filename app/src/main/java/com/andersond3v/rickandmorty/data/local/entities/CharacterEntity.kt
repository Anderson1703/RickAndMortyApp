package com.andersond3v.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=0,
    val name: String,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
