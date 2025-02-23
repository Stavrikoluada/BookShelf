package com.example.boockshelf.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.boockshelf.R
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.state.BooksUiState

@Composable
fun FavoriteScreen(
    onBookClicked: (BookModel) -> Unit,
    booksViewModel: MainViewModel
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        booksViewModel.getFavoritesBooks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(top = 64.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = colorResource(id = R.color.grig_font)
        ) {

            val booksUiState by booksViewModel.booksUiState.collectAsStateWithLifecycle()
            when (booksUiState) {
                is BooksUiState.Loading -> Loading(Modifier)
                is BooksUiState.Success -> GridScreen(
                    books = (booksUiState as BooksUiState.Success).bookSearch,
                    onBookClicked,
                )

                is BooksUiState.Error -> Error(retryAction = { booksViewModel.getFavoritesBooks() })
            }
        }
    }
}
