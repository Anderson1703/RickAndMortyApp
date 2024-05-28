package com.andersond3v.rickandmorty.data.model

data class CharacterResponse(
    val info: CharacterInfo,
    val results: List<Character>
)