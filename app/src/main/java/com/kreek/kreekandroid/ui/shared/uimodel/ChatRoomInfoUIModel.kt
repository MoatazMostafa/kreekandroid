package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

data class ChatRoomInfoUIModel(
    val userId: String = "",
    val receiverId: String = "",
    val patientId: String = "",
    val chatRoomId: String = "",
    val chatType: ChatType = ChatType.PRIVATE,
    val lastMessage: String = "",
    val lastMessageTimestamp: Long = 0
)

fun ChatRoomInfoUIModel.toDomainModel() = ChatRoomInfoDomainModel(
    userId = userId,
    receiverId = receiverId,
    patientId = patientId,
    chatRoomId = chatRoomId,
    chatType = chatType,
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp
)

fun ChatRoomInfo.toUIModel() = ChatRoomInfoUIModel(
    userId = userId,
    receiverId = receiverId,
    patientId = patientId,
    chatRoomId = chatRoomId,
    chatType = ChatType.fromString(chatType),
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp
)


