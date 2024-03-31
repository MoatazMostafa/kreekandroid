package com.kreek.kreekandroid.ui.features.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kreek.kreekandroid.ui.features.home.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.MessageItem
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
        _patientList.value = listOf( PatientUIModel(
            name = "John Doe",
            patientId = "0",
            age = 25,
            lastMessagesItem = MessageItem(
                messageText = "Today",
                messageDate = "12/1/2024",
                messageSenderId = "0",
                messageSenderName = "John Doe"
            )
        ),  PatientUIModel(
            name = "John Doe",
            patientId = "0",
            age = 25,
            lastMessagesItem = MessageItem(
                messageText = "Today",
                messageDate = "12/1/2024",
                messageSenderId = "0",
                messageSenderName = "John Doe"
            )
        ),  PatientUIModel(
            name = "John Doe",
            patientId = "0",
            age = 25,
            lastMessagesItem = MessageItem(
                messageText = "Today",
                messageDate = "12/1/2024",
                messageSenderId = "0",
                messageSenderName = "John Doe"
            )
        ),  PatientUIModel(
            name = "John Doe",
            patientId = "0",
            age = 25,
            lastMessagesItem = MessageItem(
                messageText = "Today",
                messageDate = "12/1/2024",
                messageSenderId = "0",
                messageSenderName = "John Doe"
            )
        ))
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
        //TODO Navigate to patient details screen
    }

}