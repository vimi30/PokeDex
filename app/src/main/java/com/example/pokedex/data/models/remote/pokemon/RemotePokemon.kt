package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.pokedex.data.models.domain.pokemon.Pokemon

@Serializable
data class RemotePokemon(
    @SerialName("abilities")
    val abilities: List<AbilityWithSlot>?,
    @SerialName("base_experience")
    val baseExperience: Int?,
    @SerialName("cries")
    val cries: Cries?,
    @SerialName("forms")
    val forms: List<Form>?,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>?,
    @SerialName("height")
    val height: Int?,
    @SerialName("held_items")
    val heldItems: List<HeldItem>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_default")
    val isDefault: Boolean?,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String?,
    @SerialName("moves")
    val moveWithVersions: List<MoveWithVersions>?,
    @SerialName("name")
    val name: String?,
    @SerialName("order")
    val order: Int?,
    @SerialName("past_types")
    val pastTypes: List<PastType>?,
    @SerialName("species")
    val species: Species?,
    @SerialName("sprites")
    val sprites: Sprites?,
    @SerialName("stats")
    val baseStats: List<BaseStat>?,
    @SerialName("types")
    val typeWithSlots: List<TypeWithSlot>?,
    @SerialName("weight")
    val weight: Int?
)


fun RemotePokemon.toDomainPokemon(): Pokemon {
    return Pokemon(
        name = name ?: "",
        imageUrl = sprites?.frontDefault ?: "",
        baseExperience = baseExperience ?: -1,
        forms = forms?.map { it.toDomainForm() } ?: emptyList(),
        height = height ?: -1,
        isDefault = isDefault ?: false,
        order = order ?: -1,
        id = id ?: -1,
        stats = baseStats?.map { it.toDomainStat() } ?: emptyList(),
        types = typeWithSlots?.map { it.toDomainType() } ?: emptyList(),
        abilities = abilities?.map { it.toDomainAbility() } ?: emptyList(),
        weight = weight ?: -1,
        moves = moveWithVersions?.map { it.toDomainMove() } ?: emptyList(),
        species = species?.toDomainSpecies()
            ?: com.example.pokedex.data.models.domain.pokemon.Species("", "")
    )
}