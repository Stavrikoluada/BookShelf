package com.example.boockshelf.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.boockshelf.domain.model.BookModel
import com.example.boockshelf.presentation.screens.detail_screen.DetailScreen
import com.example.boockshelf.di.BooksApplication
import com.example.boockshelf.ui.theme.BoockShelfTheme
import javax.inject.Inject


class DetailActivity: ComponentActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        val book = intent.getParcelableExtra<BookModel>(MainActivity.BOOK_TITLE_KEY)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                DetailScreen(book)
            }
        }
    }
}
