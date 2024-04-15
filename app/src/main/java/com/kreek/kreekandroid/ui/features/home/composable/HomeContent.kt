package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.uimodel.ChatData
import com.kreek.kreekandroid.ui.features.home.model.HomeTab
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.composables.CustomEditText
import com.kreek.kreekandroid.ui.shared.composables.TabContent
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    groupChatRoomsList: List<ChatData>,
    privateChatRoomsList: List<ChatData>,
    tabs: List<String>,
    selectedTabIndex: HomeTab,
    onTabClick: (HomeTab) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onChatDataClick: (ChatData) -> Unit,
    onFloatingButtonClick: () -> Unit,
    ) {
    val searchText = remember { mutableStateOf("") }
    val selectedTab = remember { mutableStateOf(selectedTabIndex) }
    Surface(modifier = modifier) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onFloatingButtonClick() },
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    containerColor = Color.Transparent
                ) {
                    CircleShapeIcon(
                        icon = R.drawable.ic_kreek_beak,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        iconTint = Color.White
                    )
                }
            },
            content = { _ ->
                Column {

                    TabContent(
                        tabsList = tabs,
                        selectedTabIndex = selectedTab.value.index,
                        onTabClick = {
                            selectedTab.value = HomeTab.fromIndex(it)
                            onTabClick(selectedTab.value)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomEditText(
                        hint = stringResource(R.string.search),
                        text = searchText
                    )
                    onSearchTextChanged(searchText.value)
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        if (selectedTab.value == HomeTab.GROUP_CHATS) {
                            groupChatRoomsList.forEach {
                                ChatDataItemContent(
                                    chatData = it,
                                    onChatDataClick = onChatDataClick
                                )
                                HorizontalDivider(color = LightGray)
                            }
                        } else if (selectedTab.value == HomeTab.PRIVATE_CHATS) {
                            privateChatRoomsList.forEach {
                                ChatDataItemContent(
                                    chatData = it,
                                    onChatDataClick = onChatDataClick
                                )
                                HorizontalDivider(color = LightGray)
                            }
                        }
                    }
                }

            })
    }
}


@Preview
@Composable
fun HomeContentPreview() {
    KreekandroidTheme {
        HomeContent(
            groupChatRoomsList = emptyList(),
            privateChatRoomsList = emptyList(),
            tabs = listOf("Patients", "Direct Messages"),
            onTabClick = { },
            onSearchTextChanged = { },
            onChatDataClick = { },
            onFloatingButtonClick = {},
            selectedTabIndex = HomeTab.GROUP_CHATS
        )
    }
}