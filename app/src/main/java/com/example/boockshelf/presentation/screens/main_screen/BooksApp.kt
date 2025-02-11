package com.example.boockshelf.presentation.screens.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.boockshelf.R
import com.example.boockshelf.data.storage.model.BookModel
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.screens.Error
import com.example.boockshelf.presentation.screens.Loading
import com.example.boockshelf.presentation.state.BooksUiState
import com.example.boockshelf.presentation.state.SearchWidgetState


@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    onBookClicked: (BookModel) -> Unit,
    booksViewModel: MainViewModel
) {

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        booksViewModel.initVm()
    }
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
            .padding(it),
            color = colorResource(id = R.color.grig_font)
        ) {

            val booksUiState by booksViewModel.booksUiState.collectAsStateWithLifecycle()
            when(booksUiState) {
            is BooksUiState.Loading -> Loading(modifier)
            is BooksUiState.Success -> BookGridScreen(
                books = (booksUiState as BooksUiState.Success).bookSearch,
                modifier = modifier,
                onBookClicked
            )
            is BooksUiState.Error -> Error(retryAction = {booksViewModel.getBooks("book")})
        }
        }
    }
}
