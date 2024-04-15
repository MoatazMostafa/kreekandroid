package com.kreek.kreekandroid.ui.features.selectdoctorchat.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.composables.CustomEditText
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray

@Composable
fun SelectDoctorChatContent(
    modifier: Modifier = Modifier,
    doctorList: List<DoctorUIModel>,
    onDoctorClicked: (DoctorUIModel) -> Unit,
    onSearchTextChanged: (String) -> Unit,
) {
    val searchText = remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(16.dp))
        CustomEditText(
            hint = stringResource(R.string.search),
            text = searchText
        )
        onSearchTextChanged(searchText.value)
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            doctorList.forEach {
                DoctorItemContent(doctor = it, onDoctorClicked = { onDoctorClicked(it) })
                HorizontalDivider(color = LightGray)
            }
        }

    }
}
@Preview
@Composable
fun SelectDoctorChatContentPreview(){
    KreekandroidTheme {
        SelectDoctorChatContent(
            doctorList = listOf(
                DoctorUIModel(
                    id = "1",
                    name = "Dr. John Doe",
                    speciality = "General Physician"
                ),
                DoctorUIModel(
                    id = "2",
                    name = "Dr. Jane Doe",
                    speciality = "Dentist"
                ),
                DoctorUIModel(
                    id = "3",
                    name = "Dr. John Doe",
                    speciality = "General Physician"
                ),
                DoctorUIModel(
                    id = "4",
                    name = "Dr. Jane Doe",
                    speciality = "Dentist"
                ),
                DoctorUIModel(
                    id = "5",
                    name = "Dr. John Doe",
                    speciality = "General Physician"
                ),
                DoctorUIModel(
                    id = "6",
                    name = "Dr. Jane Doe",
                    speciality = "Dentist"
                ),
                DoctorUIModel(
                    id = "7",
                    name = "Dr. John Doe",
                    speciality = "General Physician"
                ),
                DoctorUIModel(
                    id = "8",
                    name = "Dr. Jane Doe",
                    speciality = "Dentist"
                ),
                DoctorUIModel(
                    id = "9",
                    name = "Dr. John Doe",
                    speciality = "General Physician"
                ),
                DoctorUIModel(
                    id = "10",
                    name = "Dr. Jane Doe",
                    speciality = "Dentist"
                ),

            ),
            onDoctorClicked = {},
            onSearchTextChanged = {}
        )
    }
}


