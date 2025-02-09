package com.example.boockshelf.presentation.mainscreen.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boockshelf.data.storage.model.Book
import com.example.boockshelf.presentation.BooksUiState

@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    when(booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier)
        is BooksUiState.Success -> BookGridScreen(
            books = booksUiState.bookSearch,
            modifier = modifier,
            onBookClicked
        )
        is BooksUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
    }
}