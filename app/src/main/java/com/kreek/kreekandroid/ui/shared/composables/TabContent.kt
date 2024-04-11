package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kreek.kreekandroid.ui.theme.Gray
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.Primary

@Composable
fun TabContent(
    modifier: Modifier = Modifier,
    tabsList: List<String> = emptyList(),
    selectedTabIndex: Int = 0,
    onTabClick: (Int) -> Unit
) {
    var currentSelectedTabIndex by remember {
        mutableIntStateOf(selectedTabIndex)
    }
    Column(modifier = modifier) {
        TabRow(
            modifier = Modifier,
            selectedTabIndex = currentSelectedTabIndex,
            divider = {},
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Primary,
            indicator = @Composable { tabPositions ->
                if (currentSelectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[currentSelectedTabIndex]),
                        color = Primary
                    )
                }
            }) {
            tabsList.forEachIndexed { index, item ->
                Tab(
                    selected = index == currentSelectedTabIndex,
                    onClick = {
                        currentSelectedTabIndex = index
                        onTabClick(index)
                    },
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.headlineSmall,
                            color = if (index == currentSelectedTabIndex) MaterialTheme.colorScheme.onSurface else Gray
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun TabContentPreview() {
    KreekandroidTheme {
        TabContent(
            tabsList = listOf("Patients", "Updates", "Profile"),
            selectedTabIndex = 0,
            onTabClick = { }
        )
    }
}
