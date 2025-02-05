package com.example.boockshelf.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boockshelf.data.storage.model.Book
import com.example.boockshelf.presentation.screens.HomeScreen
import com.example.boockshelf.presentation.screens.MainAppBar


@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit,
    booksViewModel: BooksViewModel
) {
    val booksViewModel: BooksViewModel = booksViewModel
    val searchWidgetState = booksViewModel.searchWidgetState
    val searchTextState = booksViewModel.searchTextState

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = {
                    booksViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    booksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    booksViewModel.getBooks(it)
                },
                onSearchTriggered = {
                    booksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            HomeScreen(booksUiState = booksViewModel.booksUiState,
                retryAction = {booksViewModel.getBooks("book")},
                modifier = modifier,
                onBookClicked = onBookClicked
            )
        }
    }
}
