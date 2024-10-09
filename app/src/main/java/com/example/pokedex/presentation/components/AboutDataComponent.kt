package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.PokeDexTheme
import com.example.pokedex.ui.theme.SoftBeige

@Composable
fun AboutDataComponent(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        )

        Text(
            text = value,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview
@Composable
fun AboutDataComponentPreview() {
    PokeDexTheme {
        ElevatedCard(
            colors = CardDefaults.cardColors(SoftBeige)
        ) {
            AboutDataComponent( "Species", "Bulbasaur")
        }

    }
}