package com.example.boockshelf.presentation.state

import com.example.boockshelf.domain.model.BookModel

sealed class BooksUiState {
    data class Success(val bookSearch: List<BookModel>): BooksUiState()
    data object Error: BooksUiState()
    data object Loading: BooksUiState()
}