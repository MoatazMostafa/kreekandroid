package com.kreek.kreekandroid.ui.features.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.authentication.composable.CreateDoctorContent
import com.kreek.kreekandroid.ui.features.authentication.composable.VerifyNumberContent
import com.kreek.kreekandroid.ui.features.authentication.model.CurrentAuthenticationContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen
import com.kreek.kreekandroid.ui.shared.composables.loading.GeneralLoading
import com.kreek.kreekandroid.ui.shared.uimodel.LoadingState

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    authenticationViewModel: AuthenticationViewModel
) {
    BaseScreen(
        content =
        {
            when (authenticationViewModel.loadingStateValue.collectAsState().value) {
                LoadingState.LOADING -> GeneralLoading(modifier = modifier.fillMaxSize())
                else -> {
                    when (authenticationViewModel.currentAuthenticationContent.value) {
                        CurrentAuthenticationContent.VerifyNumber -> VerifyNumberContent(
                            modifier = modifier,
                            onVerifyNumber = authenticationViewModel::onVerifyNumber
                        )

                        CurrentAuthenticationContent.CreateDoctor -> CreateDoctorContent(
                            modifier = modifier,
                            onCreateDoctor = authenticationViewModel::onCreateDoctor
                        )

                        else -> {}
                    }

                }
            }
        }, viewModel = authenticationViewModel
    )
}