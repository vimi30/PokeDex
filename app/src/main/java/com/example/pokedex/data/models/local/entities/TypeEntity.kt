package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "types", primaryKeys = ["pokemonName", "typeName"])
data class TypeEntity(
    val pokemonName: String,
    val typeName: String,
    val url: String
)
