package com.example.boockshelf.presentation.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

@SuppressLint("ResourceType")
@Composable
fun DetailScreen(
    book: BookModel?,
    saveToFavorites: (BookModel?) -> Unit,
    deleteFromFavorites: (BookModel?) -> Unit,
    back: () -> Unit
) {
    val isBookSaved = remember { mutableStateOf(book?.isSavedInDatabase ?: false) }

    LaunchedEffect(book) {
        isBookSaved.value = book?.isSavedInDatabase ?: false
    }

    var imageLoaded by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("download_anim.json")
    )
    val context = LocalContext.current
    val authors = book?.authors?.joinToString(separator = ", ")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    IconButton(
                        onClick = {
                            back()
                        },
                        colors = IconButtonDefaults.iconButtonColors(Color.Gray)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu_reverse),
                            contentDescription = stringResource(R.string.back),
                            tint = Color.Black
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    book?.title?.let {
                        Text(
                            text = it,
                            fontSize = 25.sp,
                            lineHeight = 23.sp,
                        )
                    }

                    Text(
                        text = authors ?: "",
                        modifier = Modifier.padding(top = 10.dp),
                        color = Color.Gray,
                        fontSize = 20.sp,
                        lineHeight = 18.sp,
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageLoaded) {
                        LottieAnimation(
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp),
                            composition = composition,
                            reverseOnRepeat = true,
                        )
                    }

                    AsyncImage(
                        modifier = Modifier.fillMaxHeight(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(book?.imageLink?.replace("http", "https"))
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.ic_book_96),
                        onSuccess = { imageLoaded = false },
                        onError = { imageLoaded = false },
                        contentDescription = stringResource(id = R.string.content_description),
                        contentScale = ContentScale.FillHeight
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, top = 8.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (!isBookSaved.value) {
                        Button(
                            onClick = {
                                saveToFavorites(book)
                                isBookSaved.value = true
                            },
                            colors = ButtonDefaults.buttonColors(Color.Red),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(text = stringResource(id = R.string.save_to_favorites))
                        }
                    }
                    if (isBookSaved.value) {
                        Button(
                            onClick = {
                                deleteFromFavorites(book)
                                isBookSaved.value = false
                            },
                            colors = ButtonDefaults.buttonColors(Color.Gray),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(text = stringResource(id = R.string.delete_from_favorites))
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.description_book),
                        fontSize = 24.sp,
                        lineHeight = 18.sp
                    )
                    book?.description?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            lineHeight = 18.sp
                        )
                    }

                    book?.pageCount?.let {
                        Text(
                            modifier = Modifier
                                .padding(top = 12.dp, end = 14.dp),
                            text = "$it page",
                            fontSize = 18.sp,
                            maxLines = 3,
                            lineHeight = 18.sp,
                            textAlign = TextAlign.Right
                        )
                    }
                }

                Button(
                    onClick = {
                        ContextCompat.startActivity(
                            context,
                            Intent(Intent.ACTION_VIEW, Uri.parse(book?.previewLink)),
                            null
                        )
                    },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = stringResource(id = R.string.read_online))
                }
            }
        }
    }
}