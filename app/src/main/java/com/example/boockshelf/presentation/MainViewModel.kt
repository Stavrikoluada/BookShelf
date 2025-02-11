package com.example.boockshelf.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.presentation.state.BooksUiState
import com.example.boockshelf.presentation.state.SearchWidgetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {


    private val _booksUiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    var booksUiState: StateFlow<BooksUiState> = _booksUiState


    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState


    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun initVm() {
        getBooks(DEFAULT_BOOK)
    }

    fun getBooks(query: String, maxResult: Int = 40) {
        viewModelScope.launch {
            _booksUiState.value = BooksUiState.Loading
            runCatching {
                booksRepository.getRepositoryBook(query, maxResult)
            }.onSuccess { books ->
                _booksUiState.value = BooksUiState.Success(books)
            }
                .onFailure { _booksUiState.value = BooksUiState.Error }
        }
    }

    private val DEFAULT_BOOK = "book"
}

