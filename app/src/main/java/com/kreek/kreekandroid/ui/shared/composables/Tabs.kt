package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.layout.Column
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
import com.kreek.kreekandroid.ui.theme.Primary

@Composable
fun Tabs(
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
                    text = { Text(text = item) }
                )
            }
        }
    }
}
