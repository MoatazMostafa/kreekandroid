package com.kreek.kreekandroid.ui.features.patientinfo

import android.app.Application
import android.os.Bundle
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PatientInfoViewModel(
    application: Application,
    backStackEntryArguments: Bundle?,
) : BaseViewModel(
    application = application,
) {

    private val _patient = MutableStateFlow<PatientUIModel>(getMockPatient())
    var patient = _patient.asStateFlow()

    private val _patientInfoArguments =
        KreekNavDestination.PatientInfo.parseArguments(backStackEntryArguments)

    val userId = "1"
    init {
        fetchPatientInfo(_patientInfoArguments.patientId)
    }

    private fun fetchPatientInfo(chatRoomId: String) {
        //TODO("Not yet implemented")
        _patient.value = getMockPatient()
    }


    fun onPatientInfoClicked() {
        //TODO("Not yet implemented")
    }
}