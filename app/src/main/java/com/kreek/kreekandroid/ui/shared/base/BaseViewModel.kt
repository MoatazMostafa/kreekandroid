package com.kreek.kreekandroid.ui.shared.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kreek.kreekandroid.ui.shared.uimodel.ErrorMessage
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.uimodel.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel(
    protected val application: Application,
) : ViewModel() {

    //region Properties
    protected val error = MutableStateFlow<ErrorMessage?>(null)
    val generalError = error.asStateFlow()

    protected val loadingState = MutableStateFlow(LoadingState.NOT_FETCHED)
    val loadingStateValue = loadingState.asStateFlow()


    protected var navController: NavController? = null


    //endregion


    fun resetGeneralError() {
        error.value = null
    }

    fun updateNavController(navController: NavController) {
        this.navController = navController
    }


    protected fun handleServerError(errorCode: String) {
        val errorCodeNumber = errorCode.toIntOrNull()
        if (errorCodeNumber != null) {
            when (errorCodeNumber) {
                400 -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_bad_request_exception)
                    )
                }

                401 -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_un_Authorized_exception)
                    )
                }

                404 -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_not_found_exception)
                    )
                }

                422 -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_un_processable_exception)
                    )
                }

                in 500..504 -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_server_error_exception)
                    )
                }

                else -> {
                    error.value = ErrorMessage(
                        title = application.getString(R.string.http_error, errorCode),
                        message = application.getString(R.string.http_error_unknown_backend_exception)
                    )
                }
            }

        }
        //endregion
    }
}

