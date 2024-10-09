package com.example.pokedex.data.models.local.entities

import androidx.room.Entity

@Entity(tableName = "forms", primaryKeys = ["pokemonName", "formName"])
data class FormEntity(
    val pokemonName: String,
    val formName: String,
    val url: String
)
