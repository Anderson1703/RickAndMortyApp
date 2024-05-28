package com.andersond3v.rickandmorty.ui.navigation
import kotlinx.serialization.Serializable

@Serializable
object Character

@Serializable
object Episode

@Serializable
object Downloads

@Serializable
data class Details(val id:Int, val type: String )
