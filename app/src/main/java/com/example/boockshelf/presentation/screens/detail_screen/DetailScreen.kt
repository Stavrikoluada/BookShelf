package com.example.boockshelf.presentation.screens.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import com.example.boockshelf.domain.model.BookModel
import com.google.gson.annotations.Until
import kotlinx.coroutines.launch


@Composable
fun DetailScreen(
    book: BookModel?,
    saveToFavorites: (BookModel?) -> Unit
) {
    var imageLoaded by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("download_anim.json"))

    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(colorResource(id = R.color.card_font)),

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            if (imageLoaded) {
                LottieAnimation(
                    modifier = Modifier.height(100.dp).width(100.dp),
                    composition = composition,
                    reverseOnRepeat = true,
                )
            }

            AsyncImage(
                modifier = Modifier.height(200.dp),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book?.imageLink?.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_book_96),
                onSuccess = { imageLoaded = false },
                onError = { imageLoaded = false } ,
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop
            )

            book?.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = {
                    saveToFavorites(book)
            }) {
                Text(text = stringResource(id = R.string.save_to_favorites))
            }
        }
    }
}