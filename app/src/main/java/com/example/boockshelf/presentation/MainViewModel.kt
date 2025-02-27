package com.example.boockshelf.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.domain.interactor.BooksInteractor
import com.example.boockshelf.presentation.state.BooksUiState
import com.example.boockshelf.presentation.state.SearchWidgetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val booksInteractor: BooksInteractor
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
                booksInteractor.getBooks(query, maxResult)
            }.onSuccess { books ->
                _booksUiState.value = BooksUiState.Success(books)
            }
                .onFailure { _booksUiState.value = BooksUiState.Error }
        }
    }

    fun saveToFavorites(book: BookModel) {
        viewModelScope.launch {
            booksInteractor.saveBookToFavorites(book)
        }
    }

    fun getFavoritesBooks() {
        viewModelScope.launch {
            _booksUiState.value = BooksUiState.Loading
            runCatching {
                booksInteractor.getFavoritesBooks()
            }.onSuccess { books ->
                _booksUiState.value = BooksUiState.Success(books)
            }
                .onFailure { _booksUiState.value = BooksUiState.Error }
        }
    }

    fun deleteBookForId(book: BookModel) {
        viewModelScope.launch {
            booksInteractor.deleteBookById(book.id)
        }
    }

    fun getGenresBook(): List<String> {
        return booksInteractor.getGenres()
    }

    private val DEFAULT_BOOK = "book"
}

