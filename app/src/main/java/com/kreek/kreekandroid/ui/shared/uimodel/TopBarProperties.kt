package com.kreek.kreekandroid.ui.shared.uimodel

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class TopBarProperties(
    val showTopBar: Boolean = false,
    val showKreekLogo: Boolean = false,
    val showBackButton: Boolean = false,
    val topBarActionList: List<TopBarAction> = emptyList(),
    val title: String? = null,
    val backNavigationAction: (() -> Unit)? = null,
    val actions: @Composable RowScope.() -> Unit = {},
    val backButtonClick: () -> Unit = {},
    val topBarActionClick: (TopBarAction) -> Unit = {}
)