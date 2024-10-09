package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveWithVersions(
    @SerialName("move")
    val move: Move?,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>?
)

fun MoveWithVersions.toDomainMove(): com.example.pokedex.data.models.domain.pokemon.Move {
    return com.example.pokedex.data.models.domain.pokemon.Move(
        name = move?.name ?: "",
        url = move?.url ?: ""
    )
}