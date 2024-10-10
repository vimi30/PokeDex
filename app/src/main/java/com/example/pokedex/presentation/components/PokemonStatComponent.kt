package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.PokeDexTheme
import com.example.pokedex.ui.theme.SoftBeige
import com.example.pokedex.utils.capitalizeName
import kotlinx.coroutines.delay

@Composable
fun PokemonStatComponent(statLabel: String, level: Int) {
    var currentNumber by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        for ( number in 0..level){
            currentNumber = number
            delay(8)
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = capitalizeName(statLabel),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(0.2f)
        )
        Text(
            text = currentNumber.toString(),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(0.2f)
        )
        AnimatedStatBarComponent(
            indicatorProgress = level.toFloat() / 100,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
                .weight(0.6f)
        )
    }
}

@Preview
@Composable
fun PokemonStatComponentPreview() {
    PokeDexTheme {
        ElevatedCard(
            colors = CardDefaults.cardColors(SoftBeige)
        ) {
            PokemonStatComponent(statLabel = "hp", 49)
        }
    }
}

