package com.kreek.kreekandroid.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.kreek.kreekandroid.ui.features.home.composable.HomeContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(homeViewModel)
        onDispose {
            lifecycle.removeObserver(homeViewModel)
        }
    }
    BaseScreen(content = {
        val tabsList = listOf("Patients", "Direct Messages")
        HomeContent(
            modifier = modifier,
            groupChatRoomsList = homeViewModel.groupChatRoomsList.collectAsState().value,
            privateChatRoomsList = homeViewModel.privateChatRoomsList.collectAsState().value,
            userDoctor = homeViewModel.userDoctor.collectAsState().value,
            tabsList = tabsList,
            selectedTabIndex = homeViewModel.selectedTab,
            onTabClick = homeViewModel::onTabSelected,
            onSearchTextChanged = homeViewModel::onSearchTextChanged,
            onChatDataClick = homeViewModel::onChatDataClick,
            onFloatingButtonClick = homeViewModel::onFloatingButtonClick
        )
    }, viewModel = homeViewModel)
}