package com.example.boockshelf.presentation.screens.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.boockshelf.R
import com.example.boockshelf.domain.entity.BookModel

@Composable
fun BookGridScreen(
    books: List<BookModel>,
    modifier: Modifier,
    onBookClicked: (BookModel) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(books) { _, book ->
            BooksCard(book, modifier, onBookClicked)
        }
    }
}

@Composable
fun BooksCard(
    book: BookModel,
    modifier: Modifier = Modifier,
    onBookClicked: (BookModel) -> Unit
) {
    val imageLoaded = ImageRequest.Builder(context = LocalContext.current)
        .data(book.imageLink?.replace("http", "https"))
        .crossfade(true)
        .build()
    //ИСПРАВИТЬ НА Flow

    var isLoaded by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("download_anim.json"))

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp)
            .clickable { onBookClicked(book) },
        colors = CardDefaults.cardColors(colorResource(id = R.color.card_font))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            book.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 4.dp, bottom = 8.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            if (isLoaded) {
                LottieAnimation(
                    modifier = Modifier.height(100.dp).width(100.dp),
                    composition = composition,
                    reverseOnRepeat = true,
                )
            }

            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = imageLoaded,
                error = painterResource(id = R.drawable.ic_book_96),
                onSuccess = { isLoaded = false },
                onError = { isLoaded = false } ,
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop
            )
        }
    }
}

