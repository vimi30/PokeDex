package com.example.pokedex.data.models.local.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokedex.data.models.domain.pokemon.Ability
import com.example.pokedex.data.models.domain.pokemon.Form
import com.example.pokedex.data.models.domain.pokemon.Move
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.models.domain.pokemon.Species
import com.example.pokedex.data.models.domain.pokemon.Stat
import com.example.pokedex.data.models.domain.pokemon.Type
import com.example.pokedex.data.models.local.entities.AbilityEntity
import com.example.pokedex.data.models.local.entities.FormEntity
import com.example.pokedex.data.models.local.entities.MoveEntity
import com.example.pokedex.data.models.local.entities.PokemonEntity
import com.example.pokedex.data.models.local.entities.SpeciesEntity
import com.example.pokedex.data.models.local.entities.StatEntity
import com.example.pokedex.data.models.local.entities.TypeEntity

data class PokemonWithDetails(
    @Embedded val pokemon: PokemonEntity,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val abilities: List<AbilityEntity>,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val types: List<TypeEntity>,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val moves: List<MoveEntity>,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val species: SpeciesEntity,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val stats: List<StatEntity>,

    @Relation(
        parentColumn = "name",
        entityColumn = "pokemonName"
    )
    val forms: List<FormEntity>

)

fun PokemonWithDetails.toDomainPokemon(): Pokemon {
    return Pokemon(
        id = pokemon.id,
        isDefault = pokemon.isDefault,
        baseExperience = pokemon.baseExperience,
        height = pokemon.height,
        weight = pokemon.weight,
        name = pokemon.name,
        order = pokemon.order,
        imageUrl = pokemon.imageUrl,
        isFavorite = pokemon.isFavorite,
        dominantColor = pokemon.dominantColor,
        abilities = abilities.map { Ability(name = it.abilityName, url = it.url) },
        moves = moves.map { Move(name = it.moveName, url = it.url) },
        forms = forms.map { Form(name = it.formName, url = it.url) },
        stats = stats.map { Stat(baseStat = it.baseStat, name = it.statName, url = it.url) },
        types = types.map { Type(name = it.typeName, url = it.url) },
        species = Species(name = species.speciesName, url = species.url),
    )
}