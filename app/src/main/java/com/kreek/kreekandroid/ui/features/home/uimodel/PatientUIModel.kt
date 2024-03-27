package com.kreek.kreekandroid.ui.features.home.uimodel

import com.kreek.kreekandroid.ui.shared.uimodel.MessageItem

data class PatientUIModel(
    val name: String,
    val age: Int,
    val messagesItemList: List<MessageItem> = emptyList()
  //  val patientType:

)