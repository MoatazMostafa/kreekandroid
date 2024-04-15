package com.kreek.kreekandroid.ui.features.authentication

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.domain.usecases.doctor.CacheDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorByNumberUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.SetDoctorUseCase
import com.kreek.kreekandroid.ui.features.authentication.model.CurrentAuthenticationContent
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.LoadingState
import com.kreek.kreekandroid.ui.shared.uimodel.toDomainModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AuthenticationViewModel(
    application: Application,
    private val getDoctorByNumberUseCase: GetDoctorByNumberUseCase,
    private val setDoctorUseCase: SetDoctorUseCase,
    private val cacheDoctorUseCase: CacheDoctorUseCase
) : BaseViewModel(
    application = application,
) {
    private val mobileNumber = mutableStateOf("")
    val currentAuthenticationContent = mutableStateOf(CurrentAuthenticationContent.VerifyNumber)
    private val _doctorUIModel = MutableStateFlow<DoctorUIModel?>(DoctorUIModel())

    fun onVerifyNumber(number: String) {
        mobileNumber.value = number
        loadingState.value = LoadingState.LOADING
        viewModelScope.launch {
            _doctorUIModel.value = getDoctorByNumberUseCase(mobileNumber.value)?.toUIModel()

            _doctorUIModel.collect{
                loadingState.value = LoadingState.DONE
                if (_doctorUIModel.value != null) {
                    cacheDoctorUseCase(_doctorUIModel.value!!.toDomainModel())
                    navController?.popBackStack()
                    navController?.navigate(route = KreekNavDestination.Home.navComposableDestination)
                } else {
                    currentAuthenticationContent.value = CurrentAuthenticationContent.CreateDoctor
                }
            }
        }

    }

    fun onCreateDoctor(doctorUIModel: DoctorUIModel) {
        doctorUIModel.mobileNumber = mobileNumber.value
        doctorUIModel.id = UUID.randomUUID().toString()
        viewModelScope.launch {
            setDoctorUseCase(doctorUIModel.toDomainModel())
            cacheDoctorUseCase(doctorUIModel.toDomainModel())
            navController?.popBackStack()
            navController?.navigate(route = KreekNavDestination.Home.navComposableDestination)
        }
    }

}