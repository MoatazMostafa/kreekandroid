package com.kreek.kreekandroid.ui.features.selectdoctorchat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.selectdoctorchat.composable.SelectDoctorChatContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun SelectDoctorChatScreen(
    modifier: Modifier = Modifier,
    selectDoctorChatViewModel: SelectDoctorChatViewModel
) {
    BaseScreen(content = {
        SelectDoctorChatContent(
            modifier = modifier,
            doctorList = selectDoctorChatViewModel.doctorList.collectAsState().value,
            onDoctorClicked = selectDoctorChatViewModel::onDoctorClicked,
            onSearchTextChanged = selectDoctorChatViewModel::onSearchTextChanged
        )
    }, viewModel = selectDoctorChatViewModel)
}


