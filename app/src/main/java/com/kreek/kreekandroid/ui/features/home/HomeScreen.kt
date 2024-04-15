package com.kreek.kreekandroid.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.home.composable.HomeContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    BaseScreen(content = {
        val tabs = listOf("Patients", "Direct Messages")
        HomeContent(
            modifier = modifier,
            groupChatRoomsList = homeViewModel.groupChatRoomsList.collectAsState().value,
            privateChatRoomsList = homeViewModel.privateChatRoomsList.collectAsState().value,
            tabs = tabs,
            selectedTabIndex = homeViewModel.selectedTab,
            onTabClick = homeViewModel::onTabSelected,
            onSearchTextChanged = homeViewModel::onSearchTextChanged,
            onChatDataClick = homeViewModel::onChatDataClick,
            onFloatingButtonClick = homeViewModel::onFloatingButtonClick
        )
    }, viewModel = homeViewModel)
}