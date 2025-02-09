package com.example.boockshelf.presentation.mainscreen.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("download_anim.json"))

    Box(modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.height(200.dp).width(200.dp),
            composition = composition,
            reverseOnRepeat = true
        )
    }
}
