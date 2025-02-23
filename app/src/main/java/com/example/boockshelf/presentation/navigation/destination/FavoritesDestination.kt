package com.example.boockshelf.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.screens.FavoriteScreen


const val BASE_ROUTE_FAVORITE = "favorite"

fun NavGraphBuilder.favorite(
    onBookClicked: (BookModel) -> Unit,
    booksViewModel: MainViewModel
) {
    composable(BASE_ROUTE_FAVORITE) {
        FavoriteScreen(
            onBookClicked = onBookClicked,
            booksViewModel = booksViewModel
        )
    }
}

fun NavController.navigateToFavorite(
) {
    navigate(BASE_ROUTE_FAVORITE)
}