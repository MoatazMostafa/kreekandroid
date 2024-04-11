package com.kreek.kreekandroid.ui.features.patientinfo.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getIconResource
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatient
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun PatientInfoContent(
    modifier: Modifier = Modifier,
    patient: PatientUIModel,
) {

    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        CircleShapeIcon(icon = patient.patientDiagnosis.getIconResource())
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(top = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = patient.name,
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.tap_here_for_more_info),
                style = TypographyCustom.bodyXSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PatientInfoContentPreview() {
    KreekandroidTheme {
        PatientInfoContent(
            patient = getMockPatient(),
        )
    }
}