package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

data class ChatMessageUIModel(
    val chatRoomId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val timestamp: Long = 0,
    val messageType: ChatMessageType = ChatMessageType.TEXT,
    val chatType: ChatType = ChatType.PRIVATE
)

fun ChatMessageDomainModel.toUIModel() = ChatMessageUIModel(
    chatRoomId = chatRoomId,
    senderId = senderId,
    receiverId = receiverId,
    message = message,
    timestamp = timestamp,
    messageType = messageType,
    chatType = chatType
)

fun ChatMessageUIModel.toDomainModel() = ChatMessageDomainModel(
    chatRoomId = chatRoomId,
    senderId = senderId,
    receiverId = receiverId,
    message = message,
    timestamp = timestamp,
    messageType = messageType,
    chatType = chatType
)

fun ChatMessage.toUIModel() = ChatMessageUIModel(
    chatRoomId = chatRoomId,
    senderId = senderId,
    receiverId = receiverId,
    message = message,
    timestamp = timestamp,
    messageType = ChatMessageType.fromString(messageType),
    chatType = ChatType.fromString(chatType)
)