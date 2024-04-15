package com.kreek.kreekandroid.ui.features.authentication.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.composables.CustomEditText
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray

@Composable
fun CreateDoctorContent(
    modifier: Modifier = Modifier,
    onCreateDoctor: (DoctorUIModel) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val doctorName = remember { mutableStateOf("") }
        val doctorEmail = remember { mutableStateOf("") }
        val doctorDegree = remember { mutableStateOf("") }
        val doctorSpecialization = remember { mutableStateOf("") }
        val doctorDepartment = remember { mutableStateOf("") }
        val doctorHospital = remember { mutableStateOf("") }
        val signUpButtonClicked = remember { mutableStateOf(false) }
        Spacer(modifier = Modifier.height(52.dp))
        Box(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .background(color = LightGray, shape = CircleShape)
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.ic_doctor),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            text = "DR. ${doctorName.value}, ${doctorDegree.value}"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = "${stringResource(R.string.doctor_name)}*"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_name),
            text = doctorName,
            isError = doctorName.value.isEmpty() && signUpButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = "${stringResource(R.string.degree)}*"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_degree),
            text = doctorDegree,
            isError = doctorDegree.value.isEmpty() && signUpButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = "${stringResource(R.string.specialization)}*"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_specialization),
            text = doctorSpecialization,
            isError = doctorSpecialization.value.isEmpty() && signUpButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(R.string.email)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_email),
            text = doctorEmail
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = "Department"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = "Enter Your Department",
            text = doctorDepartment
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = "Hospital"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = "Enter Your Hospital",
            text = doctorHospital
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            onClick = {
                signUpButtonClicked.value = true
                if (doctorName.value.isNotBlank() && doctorDegree.value.isNotBlank() && doctorSpecialization.value.isNotBlank()) {
                    onCreateDoctor(
                        DoctorUIModel(
                            name = doctorName.value,
                            email = doctorEmail.value,
                            degree = doctorDegree.value,
                            speciality = doctorSpecialization.value,
                            department = doctorDepartment.value,
                            hospital = doctorHospital.value
                        )
                    )
                }
            }) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(R.string.create_doctor_sign_up_button)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun CreateDoctorContentPreview() {
    KreekandroidTheme {
        CreateDoctorContent(onCreateDoctor = {})
    }
}