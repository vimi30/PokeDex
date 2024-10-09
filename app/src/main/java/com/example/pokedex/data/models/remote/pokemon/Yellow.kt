package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Yellow(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_gray")
    val backGray: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_gray")
    val frontGray: String?
)