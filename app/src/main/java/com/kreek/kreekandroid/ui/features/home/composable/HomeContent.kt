package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.features.home.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.composables.CustomEditText
import com.kreek.kreekandroid.ui.shared.composables.TabContent
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    patientList: List<PatientUIModel>,
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onPatientClick: (PatientUIModel) -> Unit
) {
    val searchText = remember { mutableStateOf("") }
    Column(modifier = modifier) {
        TabContent(
            tabsList = tabs,
            selectedTabIndex = selectedTabIndex,
            onTabClick = onTabClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomEditText(hint = "Search", text = searchText)
        onSearchTextChanged(searchText.value)

        Spacer(modifier = Modifier.height(16.dp))
        patientList.forEach {
            PatientItemContent(patient = it, onPatientClick = onPatientClick )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 18.dp),
                color = LightGray,
            )
        }

    }
}

@Preview
@Composable
fun HomeContentPreview() {
    KreekandroidTheme {
        HomeContent(
            patientList = emptyList(),
            tabs = listOf("Patients", "Updates", "Profile"),
            selectedTabIndex = 0,
            onTabClick = { },
            onSearchTextChanged = { },
            onPatientClick = { }
        )
    }
}