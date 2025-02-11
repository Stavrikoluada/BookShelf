package com.example.boockshelf.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.boockshelf.presentation.screens.detail_screen.DetailScreen
import com.example.boockshelf.di.BooksApplication
import com.example.boockshelf.presentation.screens.Loading
import com.example.boockshelf.presentation.state.BooksUiStateDetail
import com.example.boockshelf.ui.theme.BoockShelfTheme
import javax.inject.Inject


class DetailActivity: ComponentActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        val bookTitle = intent.getStringExtra(MainActivity.BOOK_TITLE_KEY) ?: ""

        viewModel.getBookDetails(bookTitle)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                val state by viewModel.booksUiStateDetail.collectAsState()
                when (state) {
                    is BooksUiStateDetail.Loading -> {
                        Loading()
                    }
                    is BooksUiStateDetail.Success -> {
                        val bookDetail = (state as BooksUiStateDetail.Success)
                            .bookSearchDetail.firstOrNull()
                        if (bookDetail != null) {
                            DetailScreen(
                                book = bookDetail
                            )
                        }
                    }
                    is BooksUiStateDetail.Error -> {
                        Loading()
                    }
                }
            }
        }
    }
}
