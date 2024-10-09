package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldItem(
    @SerialName("item")
    val remoteItem: RemoteItem?,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>?
)