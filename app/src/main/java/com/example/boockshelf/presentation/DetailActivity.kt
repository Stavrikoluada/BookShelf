package com.example.boockshelf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.boockshelf.data.storage.model.DetailBook
import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.domain.repository.NetworkBooksRepository
import com.example.boockshelf.presentation.detailscreen.DetailScreen
import com.example.boockshelf.presentation.di.BooksApplication
import com.example.boockshelf.presentation.mainscreen.screens.BooksApp
import com.example.boockshelf.presentation.mainscreen.screens.LoadingScreen
import com.example.boockshelf.ui.theme.BoockShelfTheme
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailActivity: ComponentActivity() {

    @Inject
    lateinit var viewModel: BooksViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        val bookTitle = intent.getStringExtra("BOOK_TITLE") ?: ""

        viewModel.getBookDetails(bookTitle)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                val state = viewModel.booksUiStateDetail
                when (state) {
                    is BooksUiStateDetail.Loading -> {
                        LoadingScreen()
                    }
                    is BooksUiStateDetail.SuccessDetail -> {
                        val bookDetail = state.bookSearchDetail.firstOrNull()
                        if (bookDetail != null) {
                            DetailScreen(
                                detailBook = bookDetail
                            )
                        }
                    }
                    is BooksUiStateDetail.Error -> {
                        LoadingScreen()
                    }
                }
            }
        }
    }
}
