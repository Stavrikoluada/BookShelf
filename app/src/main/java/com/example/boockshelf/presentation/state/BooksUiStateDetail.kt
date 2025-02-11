package com.example.boockshelf.presentation.state

import com.example.boockshelf.data.storage.model.BookModel

sealed class BooksUiStateDetail {
    data class Success(val bookSearchDetail: List<BookModel>) : BooksUiStateDetail()
    data object Error : BooksUiStateDetail()
    data object Loading : BooksUiStateDetail()
}
