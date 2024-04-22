package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType


data class ChatMessageDomainModel(
    val chatRoomId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val firstUserId: String = "",
    val secondUserId: String = "",
    val firstUserName: String = "",
    val secondUserName: String = "",
    val patientId: String = "",
    val patientName: String = "",
    val message: String = "",
    val timestamp: Long = 0,
    val messageType: ChatMessageType = ChatMessageType.TEXT,
    val chatType: ChatType = ChatType.PRIVATE
)

fun ChatMessageDomainModel.toDataModel() = ChatMessage(
    chatRoomId = chatRoomId,
    senderId = senderId,
    receiverId = receiverId,
    firstUserId = firstUserId,
    secondUserId = secondUserId,
    firstUserName = firstUserName,
    secondUserName = secondUserName,
    patientId = patientId,
    patientName = patientName,
    message = message,
    timestamp = timestamp,
    messageType = messageType.value,
    chatType = chatType.value
)

fun ChatMessage.toDomainModel() = ChatMessageDomainModel(
    chatRoomId = chatRoomId,
    senderId = senderId,
    receiverId = receiverId,
    firstUserId = firstUserId,
    secondUserId = secondUserId,
    firstUserName = firstUserName,
    secondUserName = secondUserName,
    patientId = patientId,
    patientName = patientName,
    message = message,
    timestamp = timestamp,
    messageType = ChatMessageType.fromString(messageType),
    chatType = ChatType.fromString(chatType)
)