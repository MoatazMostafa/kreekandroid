package com.kreek.kreekandroid.ui.shared.base

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.kreek.kreekandroid.ui.shared.composables.dialog.ErrorDialog

@Composable
fun BaseScreen(
    content: @Composable () -> Unit,
    viewModel: BaseViewModel,
) {
    content()
    ErrorDialog(
        error = viewModel.generalError.collectAsState().value,
        onConfirm = { viewModel.resetGeneralError() },
        onDismiss = {}
    )
}
