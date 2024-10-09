package com.example.pokedex.data.models.remote.evolutionchain


import com.example.pokedex.data.models.remote.pokemon.Move
import com.example.pokedex.data.models.remote.pokemon.RemoteItem
import com.example.pokedex.data.models.remote.pokemon.Type
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEvolutionDetail(
    @SerialName("gender")
    val gender: Int?,
    @SerialName("held_item")
    val heldRemoteItem: RemoteItem?,
    @SerialName("item")
    val remoteItem: RemoteItem?,
    @SerialName("known_move")
    val knownMove: Move?,
    @SerialName("known_move_type")
    val knownMoveType: Type?,
    @SerialName("min_affection")
    val minAffection: Int?,
    @SerialName("min_beauty")
    val minBeauty: Int?,
    @SerialName("min_happiness")
    val minHappiness: Int?,
    @SerialName("min_level")
    val minLevel: Int?,
    @SerialName("needs_overworld_rain")
    val needsOverworldRain: Boolean?,
    @SerialName("party_type")
    val partyType: Type?,
    @SerialName("relative_physical_stats")
    val relativePhysicalStats: Int?,
    @SerialName("time_of_day")
    val timeOfDay: String?,
    @SerialName("trigger")
    val trigger: Trigger?,
    @SerialName("turn_upside_down")
    val turnUpsideDown: Boolean?
)