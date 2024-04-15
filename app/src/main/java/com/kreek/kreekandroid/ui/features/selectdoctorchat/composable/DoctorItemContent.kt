package com.kreek.kreekandroid.ui.features.selectdoctorchat.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun DoctorItemContent(
    modifier: Modifier = Modifier,
    doctor: DoctorUIModel,
    onDoctorClicked: () -> Unit
) {
    Row(modifier = modifier.padding(vertical = 8.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .clickable { onDoctorClicked() }) {
        CircleShapeIcon(
            icon =  R.drawable.ic_doctor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(top = 8.dp)) {

            Text(
                text = doctor.name,
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = doctor.speciality,
                style = TypographyCustom.bodyXSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun DoctorItemContentContentPreview() {
    KreekandroidTheme {
        DoctorItemContent(
            doctor = DoctorUIModel(
                id = "1",
                name = "Dr. John Doe",
                mobileNumber = "0123456789",
                profilePic = "",
                email = "",
                speciality = "General Physician"
            ),
            onDoctorClicked = {}
        )
    }
}