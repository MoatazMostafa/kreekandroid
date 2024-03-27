package com.kreek.kreekandroid.ui.features.home

import androidx.compose.runtime.Composable
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
       // HomeContent(homeViewModel = homeViewModel, patientListItemList = emptyList<List<PatientListItem>>())
    }, viewModel = homeViewModel)
}