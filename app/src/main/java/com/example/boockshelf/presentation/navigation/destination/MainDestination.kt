package com.example.boockshelf.presentation.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.boockshelf.domain.model.BookModel
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.screens.main_screen.BooksMainScreen

const val BASE_ROUTE_MAIN = "main"

fun NavGraphBuilder.main(
    onBookClicked: (BookModel) -> Unit,
    booksViewModel: MainViewModel
) {
    composable(BASE_ROUTE_MAIN) {
        BooksMainScreen(
            onBookClicked = onBookClicked,
            booksViewModel = booksViewModel
        )
    }
}