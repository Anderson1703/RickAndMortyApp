package com.andersond3v.rickandmorty.data.model

data class EpisodeResponse(
    val info: EpisodeInfo,
    val results: List<Episode>
)