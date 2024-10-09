package com.example.pokedex.data.models.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val isDefault: Boolean,
    val order: Int,
    val imageUrl: String,
    val weight: Int,
    //local properties
    val isFavorite: Boolean,
    val dominantColor: String
)
