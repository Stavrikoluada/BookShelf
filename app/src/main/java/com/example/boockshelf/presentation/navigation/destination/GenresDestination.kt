package com.example.boockshelf.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.boockshelf.presentation.screens.GenresScreen

const val BASE_ROUTE_GENRES = "genres"

fun NavGraphBuilder.genres(
    onGenreClicked: (String) -> Unit,
    genres: List<String>
) {
    composable(BASE_ROUTE_GENRES) {
        GenresScreen(
            genres = genres,
            onGenreClicked = onGenreClicked
        )
    }
}

fun NavController.navigateToGenres() {
    navigate(BASE_ROUTE_GENRES)
}

fun NavController.navigateToMainForGenres() {
    navigate(BASE_ROUTE_MAIN)
}