package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import com.example.pokedex.ui.theme.GhostWhite
import java.util.Locale

// Helper function to convert a Color to a hex string
fun Color.toHexString(): String = String.format("#%08X", this.toArgb())

// Helper function to convert a hex string to a Jetpack Compose Color
fun hexStringToColor(hexString: String): Color = Color(hexString.toColorInt())

fun capitalizeName(name: String): String {
    return name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}

fun extractIdFromUrl ( url: String): String {
    return if (url.endsWith("/")) {
        url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        url.takeLastWhile { it.isDigit() }
    }
}

fun getImageUrlFromPokemonUrl(pokemonUrl: String): String {
    if (pokemonUrl.isEmpty()) return ""
    val number =  extractIdFromUrl(pokemonUrl)
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
}

fun getContrastingTextColor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() > 0.7f) Color.DarkGray else GhostWhite
}