package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.features.home.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.MessageItem
import com.kreek.kreekandroid.ui.theme.DarkGray
import com.kreek.kreekandroid.ui.theme.Gray
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun PatientItemContent(
    patient: PatientUIModel,
    onPatientClick: (PatientUIModel) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onPatientClick(patient) }) {
        CircleShapeIcon()
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
                    color = Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = patient.lastMessagesItem.messageText,
                style = MaterialTheme.typography.bodySmall,
                color = DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PatientListItemModePreview() {
    KreekandroidTheme {
        PatientItemContent(
            patient = PatientUIModel(
                name = "John Doe",
                patientId = "0",
                age = 25,
                lastMessagesItem = MessageItem(
                    messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                    messageDate = "12/1/2024",
                    messageSenderId = "0",
                    messageSenderName = "John Doe"
                )
            ),
            onPatientClick = { }
        )
    }
}