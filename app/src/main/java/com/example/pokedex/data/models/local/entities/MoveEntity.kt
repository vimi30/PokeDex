package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "moves", primaryKeys = ["pokemonName", "moveName"])
data class MoveEntity(
    val pokemonName: String,
    val moveName: String,
    val url: String
)
