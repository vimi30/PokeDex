package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "abilities", primaryKeys = ["pokemonName", "abilityName"])
data class AbilityEntity(
    val pokemonName: String,
    val abilityName: String,
    val url: String
)
