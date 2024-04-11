package com.kreek.kreekandroid.ui.shared.uimodel

data class PatientUIModel(
    val name: String,
    val patientId: String,
    val age: Int,
    val messagesItemList: MutableList<MessageItem> = mutableListOf(),
    val lastMessagesItem: MessageItem,
    val patientDiagnosis:PatientDiagnosis = PatientDiagnosis.Other
)