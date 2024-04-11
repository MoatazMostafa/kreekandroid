package com.kreek.kreekandroid.ui.features.patientinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.patientinfo.composable.PatientInfoContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun PatientInfoScreen(
    modifier: Modifier = Modifier,
    patientInfoViewModel: PatientInfoViewModel
) {
    BaseScreen(content = {
        PatientInfoContent(
            modifier = modifier,
            patient = patientInfoViewModel.patient.collectAsState().value
        )
    }, viewModel = patientInfoViewModel)
}