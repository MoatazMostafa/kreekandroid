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
        val tabs = listOf("Patients", "Updates", "Profile")
        HomeContent(
            modifier = modifier,
            patientList = homeViewModel.patientList.collectAsState().value,
            tabs = tabs,
            selectedTabIndex = homeViewModel.selectedTabIndex.collectAsState().value,
            onTabClick = homeViewModel::onTabSelected,
            onSearchTextChanged = homeViewModel::onSearchTextChanged,
            onPatientClick = homeViewModel::onPatientClick
        )
    }, viewModel = homeViewModel)
}