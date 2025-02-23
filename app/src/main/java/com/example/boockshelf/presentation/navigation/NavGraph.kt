package com.example.boockshelf.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.navigation.destination.BASE_ROUTE_MAIN
import com.example.boockshelf.presentation.navigation.destination.details
import com.example.boockshelf.presentation.navigation.destination.favorite
import com.example.boockshelf.presentation.navigation.destination.genres
import com.example.boockshelf.presentation.navigation.destination.main
import com.example.boockshelf.presentation.navigation.destination.navigateToDetails
import com.example.boockshelf.presentation.navigation.destination.navigateToMainForGenres

@Composable
fun NavGraph(
    navController: NavHostController,
    booksViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BASE_ROUTE_MAIN
    ) {

        main(
            onBookClicked = { bookModel ->
                navController.navigateToDetails(bookModel)
            },
            booksViewModel = booksViewModel
        )
        details(
            saveToFavorites = { bookModel ->
                if (bookModel != null) {
                    booksViewModel.saveToFavorites(bookModel)
                }
            },
            deleteFromFavorites = { bookModel ->
                if (bookModel != null) {
                    booksViewModel.deleteBookForId(bookModel)
                }
            }
        )
        favorite(
            onBookClicked = { bookModel ->
                navController.navigateToDetails(bookModel)
            },
            booksViewModel = booksViewModel
        )
        genres(
            genres = booksViewModel.getGenresBook(),
            onGenreClicked = { genre ->
                booksViewModel.getBooks(genre)
                navController.navigateToMainForGenres()
            }

        )
    }
}

