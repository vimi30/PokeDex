package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "species", primaryKeys = ["pokemonName", "speciesName"])
data class SpeciesEntity(
    val pokemonName: String,
    val speciesName: String,
    val url: String
)
