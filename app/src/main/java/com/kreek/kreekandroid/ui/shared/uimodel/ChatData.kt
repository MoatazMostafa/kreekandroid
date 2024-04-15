package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.DoctorChatDataDomainModel

data class ChatData(
    val chatRoomInfoDomainModel: ChatRoomInfoDomainModel,
    val doctorUIModel: DoctorUIModel? = null,
    val patientUIModel: PatientUIModel? = null,
)

fun DoctorChatDataDomainModel.toChatData(): ChatData =
    ChatData(
        chatRoomInfoDomainModel = chatRoomInfoDomainModel,
        doctorUIModel = doctor.toUIModel(),
    )
//TODO
//fun PatientChatDomainModel.toChatData(): ChatData =
//    ChatData(
//        userChatRoomDomainModel = userChatRoomDomainModel,
//        PatientUIModel = doctor.toUIModel(),
//    )