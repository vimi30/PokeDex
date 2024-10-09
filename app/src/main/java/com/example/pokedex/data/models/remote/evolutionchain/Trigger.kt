package com.example.pokedex.data.models.remote.evolutionchain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trigger(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)