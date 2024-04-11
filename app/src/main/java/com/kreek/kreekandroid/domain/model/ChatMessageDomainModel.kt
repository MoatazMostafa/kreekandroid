package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType


data class ChatMessageDomainModel(
    val senderId: String,
    val chatRoomId: String,
    val senderName: String = "",
    val receiverId: String = "",
    val patientId: String = "",
    val message: String,
    val timestamp: Long,
    val messageType: ChatMessageType = ChatMessageType.TEXT,
    val chatType: ChatType = ChatType.PRIVATE
)

fun ChatMessageDomainModel.toDataModel() = ChatMessage(
    senderId = senderId,
    chatRoomId = chatRoomId,
    senderName = senderName,
    receiverId = receiverId,
    patientId = patientId,
    message = message,
    timestamp = timestamp,
    messageType = messageType.value,
    chatType = chatType.value
)

fun ChatMessage.toDomainModel() = ChatMessageDomainModel(
    senderId = senderId,
    chatRoomId = chatRoomId,
    senderName = senderName,
    receiverId = receiverId,
    patientId = patientId,
    message = message,
    timestamp = timestamp,
    messageType = ChatMessageType.fromString(messageType),
    chatType = ChatType.fromString(chatType)
)