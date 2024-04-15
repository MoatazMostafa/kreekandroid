package com.kreek.kreekandroid.ui.features.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application,
    val getCachedDoctorUseCase: GetCachedDoctorUseCase
) : BaseViewModel(
    application = application,
) {
    private var _isScreenClosed = MutableStateFlow(false)
    private var userDoctor: DoctorDomainModel? = null

    init {
        viewModelScope.launch {
            userDoctor = getCachedDoctorUseCase()
        }
    }


    fun onStartSplash() {
        viewModelScope.launch {
            delay(3000)
            navController?.popBackStack()
            if (userDoctor == null) {
                navController?.navigate(route = KreekNavDestination.Authentication.navComposableDestination)
            } else {
                navController?.navigate(route = KreekNavDestination.Home.navComposableDestination)

            }
        }
    }

    fun onStopSplash() {
        _isScreenClosed.value = true
    }
}