package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getIconResource
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatient
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun PatientItemContent(
    modifier: Modifier = Modifier,
    patient: PatientUIModel,
    onPatientClick: (PatientUIModel) -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onPatientClick(patient) }
        .padding(vertical = 16.dp)) {
        CircleShapeIcon(icon = patient.patientDiagnosis.getIconResource())
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = patient.name,
                    style = TypographyCustom.headlineXSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = patient.lastMessagesItem.messageDate,
                    style = TypographyCustom.bodyXSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = patient.lastMessagesItem.messageText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PatientListItemModePreview() {
    KreekandroidTheme {
        PatientItemContent(
            patient = getMockPatient(),
            onPatientClick = { }
        )
    }
}