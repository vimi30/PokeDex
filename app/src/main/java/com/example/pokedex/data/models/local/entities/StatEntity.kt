package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "stats", primaryKeys = ["pokemonName", "statName"])
data class StatEntity(
    val pokemonName: String,
    val baseStat: Int,
    val statName: String,
    val url: String
)