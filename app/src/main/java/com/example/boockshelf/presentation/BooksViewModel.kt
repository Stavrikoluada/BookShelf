package com.example.boockshelf.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.data.storage.model.Book
import com.example.boockshelf.data.storage.model.DetailBook
import kotlinx.coroutines.launch
import okio.IOException

sealed interface BooksUiState {
    data class Success(val bookSearch: List<Book>): BooksUiState
    object Error: BooksUiState
    object Loading: BooksUiState
}

sealed interface BooksUiStateDetail {
    data class SuccessDetail(val bookSearchDetail: List<DetailBook>): BooksUiStateDetail
    object Error: BooksUiStateDetail
    object Loading: BooksUiStateDetail
}

class BooksViewModel (
    val booksRepository: BooksRepository
): ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set
    var booksUiStateDetail: BooksUiStateDetail by mutableStateOf(BooksUiStateDetail.Loading)
        private set


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

    init {
        getBooks("book")
    }

    fun getBooks(query: String, maxResult: Int = 40) {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                BooksUiState.Success(booksRepository.getRepositoryBook(query, maxResult))
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            }
        }
    }

    fun getBookDetails(query: String,  maxResult: Int = 1) {
        viewModelScope.launch {
            booksUiStateDetail = BooksUiStateDetail.Loading
            booksUiStateDetail = try {
                val bookDetail = booksRepository.getRepositoryDetailBook(query, maxResult)
                if (bookDetail != null) {
                    BooksUiStateDetail.SuccessDetail(bookDetail)
                } else {
                    BooksUiStateDetail.Error
                }
            } catch (e: IOException) {
                BooksUiStateDetail.Error
            } catch (e: HttpException) {
                BooksUiStateDetail.Error
            }
        }
    }
}

enum class SearchWidgetState {
    OPENED,
    CLOSED
}