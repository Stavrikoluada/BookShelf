package com.example.boockshelf.presentation.state

import com.example.boockshelf.domain.entity.BookModel

sealed class BooksUiState {
    data class Success(val bookSearch: List<BookModel>): BooksUiState()
    data object Error: BooksUiState()
    data object Loading: BooksUiState()
}