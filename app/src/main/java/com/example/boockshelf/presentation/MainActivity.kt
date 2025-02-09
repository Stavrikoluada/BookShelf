package com.example.boockshelf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.example.boockshelf.presentation.di.BooksApplication
import com.example.boockshelf.presentation.mainscreen.screens.BooksApp
import com.example.boockshelf.ui.theme.BoockShelfTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                BooksApp(
                    onBookClicked = { book ->
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("BOOK_TITLE", book.title)
                        startActivity(intent)
                    },
                    booksViewModel = viewModel
                )
            }
        }
    }
}
