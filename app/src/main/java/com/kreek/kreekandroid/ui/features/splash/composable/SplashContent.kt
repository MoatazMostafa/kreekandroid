package com.kreek.kreekandroid.ui.features.splash.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme


@Composable
fun SplashContent(
    modifier: Modifier,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    var state by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true, block = {
        onStart()
        state = true
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            onStop()
        }
    })

    Box(modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .background(White)
        ) {
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize(),
            visible = state,
            enter = fadeIn() + slideInVertically(initialOffsetY = { 200 }),
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (logo) = createRefs()
                Box(
                    modifier = Modifier
                        .width(232.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_kreek_logo),
                        contentDescription = null,
                    )

                }

            }
        }

    }
}

@Preview
@Composable
fun SplashContentPreview() {
    KreekandroidTheme {
        SplashContent(Modifier.fillMaxSize(), {}, {})
    }
}