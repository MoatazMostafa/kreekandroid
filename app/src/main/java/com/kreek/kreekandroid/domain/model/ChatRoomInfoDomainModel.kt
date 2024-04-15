package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType

data class ChatRoomInfoDomainModel(
    val userId: String = "",
    val receiverId: String = "",
    val patientId: String = "",
    val chatRoomId: String = "",
    val chatType: ChatType = ChatType.PRIVATE,
    val lastMessage: String = "",
    val lastMessageTimestamp: Long = 0
)

fun ChatRoomInfoDomainModel.toDataModel() = ChatRoomInfo(
    userId = userId,
    receiverId = receiverId,
    patientId = patientId,
    chatRoomId = chatRoomId,
    chatType = chatType.value,
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp
)

fun ChatRoomInfo.toDomainModel() = ChatRoomInfoDomainModel(
    userId = userId,
    receiverId = receiverId,
    patientId = patientId,
    chatRoomId = chatRoomId,
    chatType = ChatType.fromString(chatType),
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp
)