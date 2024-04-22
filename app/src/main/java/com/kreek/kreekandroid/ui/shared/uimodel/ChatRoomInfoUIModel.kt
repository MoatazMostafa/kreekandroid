package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

data class ChatRoomInfoUIModel(
    val chatRoomId: String,
    var firstUserId: String = "",
    var secondUserId: String = "",
    var firstUserName: String = "",
    var secondUserName: String = "",
    var firstUserSpeciality: String = "",
    var secondUserSpeciality: String = "",
    var patientId: String = "",
    var patientName: String = "",
    val chatType: ChatType = ChatType.PRIVATE,
)

fun ChatRoomInfoUIModel.toDomainModel(): ChatRoomInfoDomainModel {
    return ChatRoomInfoDomainModel(
        chatRoomId = chatRoomId,
        firstUserId = firstUserId,
        secondUserId = secondUserId,
        firstUserName = firstUserName,
        secondUserName = secondUserName,
        firstUserSpeciality = firstUserSpeciality,
        secondUserSpeciality = secondUserSpeciality,
        patientId = patientId,
        patientName = patientName,
        chatType = chatType,
    )
}

fun ChatRoomInfoDomainModel.toUIModel(): ChatRoomInfoUIModel {
    return ChatRoomInfoUIModel(
        chatRoomId = chatRoomId,
        firstUserId = firstUserId,
        secondUserId = secondUserId,
        firstUserName = firstUserName,
        secondUserName = secondUserName,
        firstUserSpeciality = firstUserSpeciality,
        secondUserSpeciality = secondUserSpeciality,
        patientId = patientId,
        patientName = patientName,
        chatType = chatType,
    )
}

fun ChatRoomInfo.toUIModel(): ChatRoomInfoUIModel {
    return ChatRoomInfoUIModel(
        chatRoomId = chatRoomId,
        firstUserId = firstUserId,
        secondUserId = secondUserId,
        firstUserName = firstUserName,
        secondUserName = secondUserName,
        firstUserSpeciality = firstUserSpeciality,
        secondUserSpeciality = secondUserSpeciality,
        patientId = patientId,
        patientName = patientName,
        chatType = ChatType.fromString(chatType),
    )
}