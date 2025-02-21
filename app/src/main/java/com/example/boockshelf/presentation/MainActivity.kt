package com.example.boockshelf.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.boockshelf.di.BooksApplication
import com.example.boockshelf.presentation.navigation.DrawerNavigation
import com.example.boockshelf.ui.theme.BoockShelfTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as BooksApplication).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            BoockShelfTheme {
                val navController = rememberNavController()
                DrawerNavigation(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}



//С помощью room сделать возможным сохранение избранных книг
//Сделать навигацию с синглактивити и шторкой слева
//Поправить экраны
//В классе, где показывают карточки сделать оптимизацию по ID
