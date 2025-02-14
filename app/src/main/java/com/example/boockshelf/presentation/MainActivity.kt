package com.example.boockshelf.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.boockshelf.di.BooksApplication
import com.example.boockshelf.presentation.screens.main_screen.BooksApp
import com.example.boockshelf.ui.theme.BoockShelfTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                BooksApp(
                    onBookClicked = { book ->
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra(BOOK_TITLE_KEY, book)
                        startActivity(intent)
                    },
                    booksViewModel = viewModel
                )
            }
        }
    }
    companion object {
        const val BOOK_TITLE_KEY = "BOOK_TITLE"
    }
}


//С помощью room сделать возможным сохранение избранных книг
//Сделать навигацию с синглактивити и шторкой слева
//Поправить экраны
