package com.example.boockshelf.presentation.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.screens.MainScreen

const val BASE_ROUTE_MAIN = "main"

fun NavGraphBuilder.main(
    onBookClicked: (BookModel) -> Unit,
    booksViewModel: MainViewModel
) {
    composable(BASE_ROUTE_MAIN) {
        MainScreen(
            onBookClicked = onBookClicked,
            booksViewModel = booksViewModel
        )
    }
}