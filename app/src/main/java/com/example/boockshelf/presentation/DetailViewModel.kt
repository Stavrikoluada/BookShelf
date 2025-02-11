package com.example.boockshelf.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.presentation.state.BooksUiState
import com.example.boockshelf.presentation.state.BooksUiStateDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _booksUiStateDetail =
        MutableStateFlow<BooksUiStateDetail>(BooksUiStateDetail.Loading)
    val booksUiStateDetail: StateFlow<BooksUiStateDetail> = _booksUiStateDetail

    //ИСПРАВИТЬ НА ДРУГОЙ МЕТОД ПОЛУЧЕНИЯ ДАННЫХ БЕЗ ПОВТОРНОГО ОБРАЩЕНИЯ К API
    fun getBookDetails(query: String, maxResult: Int = 1) {
        viewModelScope.launch {
            _booksUiStateDetail.value = BooksUiStateDetail.Loading
            runCatching {
                booksRepository.getRepositoryBook(query, maxResult)
            }.onSuccess { books ->
                _booksUiStateDetail.value = BooksUiStateDetail.Success(books) }
                .onFailure { _booksUiStateDetail.value = BooksUiStateDetail.Error }
        }
    }
}

