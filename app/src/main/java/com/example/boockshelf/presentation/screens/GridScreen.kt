package com.example.boockshelf.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.boockshelf.R
import com.example.boockshelf.domain.entity.BookModel

@Composable
fun GridScreen(
    books: List<BookModel>,
    onBookClicked: (BookModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(books) { _, book ->
            BooksCard(book, onBookClicked)
        }
    }
}

@Composable
fun BooksCard(
    book: BookModel,
    onBookClicked: (BookModel) -> Unit
) {
    val imageLoaded = ImageRequest.Builder(context = LocalContext.current)
        .data(book.imageLink?.replace("http", "https"))
        .crossfade(true)
        .build()

    var isLoaded by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("download_anim.json")
    )
    val context = LocalContext.current
    val authors = book.authors?.joinToString(separator = ", ")

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(380.dp)
            .clickable { onBookClicked(book) },
        colors = CardDefaults.cardColors(colorResource(id = R.color.white))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (isLoaded) {
                    LottieAnimation(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                        composition = composition,
                        reverseOnRepeat = true,
                    )
                }

                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = imageLoaded,
                    error = painterResource(id = R.drawable.ic_book_96),
                    onSuccess = { isLoaded = false },
                    onError = { isLoaded = false },
                    contentDescription = stringResource(id = R.string.content_description),
                    contentScale = ContentScale.Crop
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,

                ) {
                book.title?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        maxLines = 3,
                        lineHeight = 12.sp,
                    )
                }

                Text(
                    text = authors ?: "",
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    maxLines = 2,
                    lineHeight = 12.sp,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp, bottom = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        ContextCompat.startActivity(
                            context,
                            Intent(Intent.ACTION_VIEW, Uri.parse(book.previewLink)),
                            null
                        )
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    shape = RoundedCornerShape(6.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.read_online),
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}