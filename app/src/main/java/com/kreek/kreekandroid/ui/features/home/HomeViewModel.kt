package com.kreek.kreekandroid.ui.features.home

import android.app.Application
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatientList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(application: Application) : BaseViewModel(
    application = application
) {
    private val _patientList = MutableStateFlow<List<PatientUIModel>>(emptyList())
    var patientList = _patientList.asStateFlow()

    private var _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()

    init {
        fetchPatientList()
    }

    private fun fetchPatientList() {
        //TODO("Not yet implemented")
        _patientList.value = getMockPatientList() + getMockPatientList() + getMockPatientList()
    }

    fun onTabSelected(tabIndex: Int) {
        _selectedTabIndex.value = tabIndex
        when (tabIndex) {
            0 -> {}
            1 -> {}
            2 -> {}
        }
    }

    fun onSearchTextChanged(text: String) {
        // TODO filter patient list based on search text
    }

    fun onPatientClick(patientUIModel: PatientUIModel) {
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(chatRoomId = patientUIModel.patientId).route
        )
    }

    fun onFloatingButtonClick() {
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(chatRoomId = "1", chatRoomType = "chat_bot").route
        )
    }

}