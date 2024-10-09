package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteItem(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)