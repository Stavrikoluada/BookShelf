package com.example.boockshelf.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boockshelf.R

@Composable
fun GenresScreen(
    genres: List<String>,
    onGenreClicked: (String) -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(top = 56.dp),
        color = colorResource(id = R.color.grig_font)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(genres) { _, genre ->
                GenresCard(genre, onGenreClicked)
            }
        }
    }
}

@Composable
fun GenresCard(
    genre: String,
    onGenreClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(50.dp)
            .clickable { onGenreClicked(genre) },
        colors = CardDefaults.cardColors(colorResource(id = R.color.white))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = genre,
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp),
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
        }
    }
}
