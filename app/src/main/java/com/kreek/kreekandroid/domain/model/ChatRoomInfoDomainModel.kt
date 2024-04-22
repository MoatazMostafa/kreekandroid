package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType

data class ChatRoomInfoDomainModel(
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

fun ChatRoomInfo.toDomainModel(): ChatRoomInfoDomainModel {
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
        chatType = ChatType.fromString(chatType),
    )
}

fun ChatRoomInfoDomainModel.toDataModel(): ChatRoomInfo {
    return ChatRoomInfo(
        chatRoomId = chatRoomId,
        firstUserId = firstUserId,
        secondUserId = secondUserId,
        firstUserName = firstUserName,
        secondUserName = secondUserName,
        firstUserSpeciality = firstUserSpeciality,
        secondUserSpeciality = secondUserSpeciality,
        patientId = patientId,
        patientName = patientName,
        chatType = chatType.value,
    )
}

fun ChatRoomInfoDomainModel.toChatRoomMessage(): ChatRoomMessages {
    return  ChatRoomMessages(
        chatRoomId = chatRoomId,
        firstUserId = firstUserId,
        secondUserId = secondUserId,
        firstUserName = firstUserName,
        secondUserName = secondUserName,
        firstUserSpeciality = firstUserSpeciality,
        secondUserSpeciality = secondUserSpeciality,
        patientId = patientId,
        patientName = patientName,
    )
}