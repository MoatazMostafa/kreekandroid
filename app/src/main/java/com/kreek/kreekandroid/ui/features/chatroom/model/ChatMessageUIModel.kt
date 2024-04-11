package com.kreek.kreekandroid.ui.features.chatroom.model

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

data class ChatMessageUIModel(
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
fun ChatMessageUIModel.toDomainModel() = ChatMessageDomainModel(
    senderId = senderId,
    chatRoomId = chatRoomId,
    senderName = senderName,
    receiverId = receiverId,
    patientId = patientId,
    message = message,
    timestamp = timestamp,
    messageType = messageType,
    chatType = chatType
)

fun ChatMessageDomainModel.toUIModel() = ChatMessageUIModel(
    senderId = senderId,
    chatRoomId = chatRoomId,
    senderName = senderName,
    receiverId = receiverId,
    patientId = patientId,
    message = message,
    timestamp = timestamp,
    messageType = messageType,
    chatType = chatType
)
fun ChatMessage.toUIModel() = ChatMessageUIModel(
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

enum class ChatRoomType(val value: String) {
    PRIVATE("private"),
    GROUP("group"),
    CHAT_BOT("chat_bot");

    companion object {
        fun fromString(value: String): ChatRoomType {
            return values().firstOrNull { it.value == value } ?: PRIVATE
        }
    }
}
