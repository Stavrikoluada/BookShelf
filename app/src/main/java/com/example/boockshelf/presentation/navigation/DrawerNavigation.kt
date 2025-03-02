package com.example.boockshelf.presentation.navigation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.example.boockshelf.R
import com.example.boockshelf.presentation.MainViewModel
import com.example.boockshelf.presentation.navigation.destination.navigateToFavorite
import com.example.boockshelf.presentation.navigation.destination.navigateToGenres
import com.example.boockshelf.presentation.screens.SearchBar
import com.example.boockshelf.presentation.state.SearchWidgetState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book_header),
                        contentDescription = "Header",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        "Menu",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = { Text("Books by genre") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigateToGenres()
                                drawerState.close()
                            }
                        }
                    )
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = { Text("Find a book") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                viewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                                drawerState.close()
                            }
                        }
                    )
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = { Text("Favorites") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigateToFavorite()
                                drawerState.close()
                            }
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                    )

                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        //badge = { Text("20") }, // Placeholder
                        onClick = {
                            scope.launch {
                                val intent =
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.parse("package:${context.packageName}")
                                    }
                                startActivity(context, intent, null)
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Book Shelf") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        when (viewModel.searchWidgetState.value) {
                            SearchWidgetState.CLOSED -> {
                                IconButton(onClick = {
                                    viewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                                }) {
                                    Icon(Icons.Filled.Search, contentDescription = "Search")
                                }
                            }

                            SearchWidgetState.OPENED -> {
                                SearchBar(
                                    text = viewModel.searchTextState.value,
                                    onTextChange = { newText ->
                                        viewModel.updateSearchTextState(
                                            newText
                                        )
                                    },
                                    onCloseClicked = {
                                        viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                                        viewModel.updateSearchTextState("")
                                    },
                                    onSearchClicked = { query ->
                                        viewModel.getBooks(query)
                                    }
                                )
                            }
                        }
                    }
                )
            }
        ) {
            NavGraph(
                navController = navController,
                booksViewModel = viewModel
            )
        }
    }
}