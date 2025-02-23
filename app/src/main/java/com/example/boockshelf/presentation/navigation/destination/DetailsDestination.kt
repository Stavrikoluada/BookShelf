package com.example.boockshelf.presentation.navigation.destination

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.presentation.screens.DetailScreen
import com.google.gson.Gson

private const val BASE_ROUTE = "details"
private const val BOOK_KEY = "book"

fun NavGraphBuilder.details(
    saveToFavorites: (BookModel?) -> Unit,
    deleteFromFavorites: (BookModel?) -> Unit
) {
    composable(
        route = "$BASE_ROUTE/{$BOOK_KEY}",
        arguments = listOf(
            navArgument(BOOK_KEY) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val bookJson = navBackStackEntry.arguments?.getString(BOOK_KEY)
        val book = Gson().fromJson(bookJson, BookModel::class.java)
        DetailScreen(
            book = book,
            saveToFavorites = saveToFavorites,
            deleteFromFavorites = deleteFromFavorites
        )
    }
}

fun NavController.navigateToDetails(
    book: BookModel
) {
    val encodedBook = Uri.encode(Gson().toJson(book))
    navigate("$BASE_ROUTE/$encodedBook")
}