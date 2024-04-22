package com.kreek.kreekandroid.data.firebase.chat.model

import com.google.firebase.database.DataSnapshot

data class ChatMessage(
    val chatRoomId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val firstUserId: String = "",
    val secondUserId: String = "",
    val firstUserName:String = "",
    val secondUserName:String = "",
    val patientId: String = "",
    val patientName: String = "",
    val message: String = "",
    val timestamp: Long = 0,
    val messageType: String = "text",
    val chatType: String = "private"
)

enum class ChatMessageType(val value: String) {
    TEXT("text"),
    IMAGE("image"),
    VIDEO("video"),
    AUDIO("audio"),
    FILE("file");

    companion object {
        fun fromString(value: String): ChatMessageType {
            return entries.firstOrNull { it.value == value } ?: TEXT
        }
    }
}

enum class ChatType(val value: String) {
    PRIVATE("private"),
    GROUP("group"),
    VECTARA_CHAT_BOT("vectara_chat_bot");

    companion object {
        fun fromString(value: String): ChatType {
            return entries.firstOrNull { it.value == value } ?: PRIVATE
        }
    }
}

fun mapSnapshotToChatMessage(snapshot: DataSnapshot): ChatMessage {
    val chatRoomId = snapshot.child("chatRoomId").getValue(String::class.java)
    val senderId = snapshot.child("senderId").getValue(String::class.java)
    val receiverId = snapshot.child("receiverId").getValue(String::class.java)
    val firstUserId = snapshot.child("firstUserId").getValue(String::class.java)
    val secondUserId = snapshot.child("secondUserId").getValue(String::class.java)
    val firstUserName = snapshot.child("firstUserName").getValue(String::class.java)
    val secondUserName = snapshot.child("secondUserName").getValue(String::class.java)
    val patientId = snapshot.child("patientId").getValue(String::class.java)
    val patientName = snapshot.child("patientName").getValue(String::class.java)
    val message = snapshot.child("message").getValue(String::class.java)
    val timestamp = snapshot.child("timestamp").getValue(Long::class.java)
    val messageType = snapshot.child("messageType").getValue(String::class.java)
    val chatType = snapshot.child("chatType").getValue(String::class.java)


    return ChatMessage(
        chatRoomId = chatRoomId ?: "",
        senderId = senderId ?: "",
        receiverId = receiverId ?: "",
        firstUserId = firstUserId ?: "",
        secondUserId = secondUserId ?: "",
        firstUserName = firstUserName ?: "",
        secondUserName = secondUserName ?: "",
        patientId = patientId ?: "",
        patientName = patientName ?: "",
        message = message ?: "",
        timestamp = timestamp ?: 0,
        messageType = messageType ?: "",
        chatType = chatType ?: ""
    )
}