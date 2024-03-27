package com.kreek.kreekandroid.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.splash.composable.SplashContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel
) {
    BaseScreen(content = {
        SplashContent(
            modifier = modifier,
            onStart = splashViewModel::onStartSplash,
            onStop = splashViewModel::onStopSplash
        )
    }, viewModel = splashViewModel)
}