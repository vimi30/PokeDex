package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Form(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)

fun Form.toDomainForm(): com.example.pokedex.data.models.domain.pokemon.Form {
    return com.example.pokedex.data.models.domain.pokemon.Form(
        name = name ?: "",
        url = url ?: ""
    )
}