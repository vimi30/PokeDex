package com.example.pokedex.data.models.domain.pokemon

import com.example.pokedex.data.models.local.entities.AbilityEntity
import com.example.pokedex.data.models.local.entities.FormEntity
import com.example.pokedex.data.models.local.entities.MoveEntity
import com.example.pokedex.data.models.local.entities.PokemonEntity
import com.example.pokedex.data.models.local.entities.SpeciesEntity
import com.example.pokedex.data.models.local.entities.StatEntity
import com.example.pokedex.data.models.local.entities.TypeEntity

data class Pokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val isDefault: Boolean,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val imageUrl: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    //local properties
    val isFavorite: Boolean = false,
    val dominantColor: String = ""

)

fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        isDefault = isDefault,
        baseExperience = baseExperience,
        order = order,
        height = height,
        imageUrl = imageUrl,
        weight = weight,
        isFavorite = isFavorite,
        dominantColor = dominantColor
    )
}

fun Pokemon.toAbilityEntities(): List<AbilityEntity> {
    return abilities.map { ability ->
        AbilityEntity(
            pokemonName = this.name,
            abilityName = ability.name,
            url = ability.url
        )
    }
}

fun Pokemon.toTypeEntities(): List<TypeEntity> {
    return types.map { type ->
        TypeEntity(
            pokemonName = this.name,
            typeName = type.name,
            url = type.url
        )
    }
}

fun Pokemon.toMoveEntities(): List<MoveEntity> {
    return moves.map { move ->
        MoveEntity(
            pokemonName = this.name,
            moveName = move.name,
            url = move.url
        )
    }
}

fun Pokemon.toStatEntities(): List<StatEntity> {
    return stats.map { stat ->
        StatEntity(
            pokemonName = this.name,
            baseStat = stat.baseStat,
            statName = stat.name,
            url = stat.url
        )
    }
}

fun Pokemon.toFormEntities(): List<FormEntity> {
    return forms.map { form ->
        FormEntity(
            pokemonName = this.name,
            formName = form.name,
            url = form.url
        )
    }
}

fun Pokemon.toSpeciesEntity(): SpeciesEntity {
    return SpeciesEntity(
        pokemonName = this.name,
        speciesName = species.name,
        url = species.url
    )
}

