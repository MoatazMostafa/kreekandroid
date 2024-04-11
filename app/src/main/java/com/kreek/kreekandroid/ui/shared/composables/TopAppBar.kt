package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.uimodel.TopBarAction
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.Primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    showTopBar: Boolean = false,
    showKreekLogo: Boolean = false,
    showBackButton: Boolean = false,
    topBarActionList: List<TopBarAction> = emptyList(),
    onBackButtonClick: () -> Unit,
    onTopBarActionClick: (TopBarAction) -> Unit
) {
    if (showTopBar) {
        TopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors()
                .copy(containerColor = MaterialTheme.colorScheme.background),
            title = {
                if (showKreekLogo) {
                    Icon(
                        painter = painterResource(R.drawable.ic_kreek_text),
                        tint = Primary,
                        contentDescription = ""
                    )
                }
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = { onBackButtonClick() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
                }
            },
            actions = {
                Row {
                    topBarActionList.forEach {
                        Spacer(modifier = Modifier.width(4.dp))
                        CircleShapeButton(icon = it.icon, onClick = {
                            onTopBarActionClick(it)
                        })
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarModePreview() {
    KreekandroidTheme {
        TopAppBar(
            showKreekLogo = true,
            showBackButton = true,
            topBarActionList = listOf(TopBarAction.Profile, TopBarAction.Search, TopBarAction.Menu),
            onBackButtonClick = {},
            onTopBarActionClick = {})
    }
}