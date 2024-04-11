package com.kreek.kreekandroid.data.firebase.chatmessage.model

import com.google.firebase.database.DataSnapshot

data class ChatMessage(
    val senderId: String,
    val chatRoomId: String,
    val senderName: String = "",
    val receiverId: String = "",
    val patientId: String = "",
    val message: String,
    val timestamp: Long,
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
    GROUP("group");

    companion object {
        fun fromString(value: String): ChatType {
            return entries.firstOrNull { it.value == value } ?: PRIVATE
        }
    }
}

fun mapSnapshotToChatMessage(snapshot: DataSnapshot): ChatMessage {
    val senderId = snapshot.child("senderId").getValue(String::class.java)
    val chatRoomId = snapshot.child("chatRoomId").getValue(String::class.java)
    val senderName = snapshot.child("senderName").getValue(String::class.java)
    val receiverId = snapshot.child("receiverId").getValue(String::class.java)
    val patientId = snapshot.child("patientId").getValue(String::class.java)
    val message = snapshot.child("message").getValue(String::class.java)
    val timestamp = snapshot.child("timestamp").getValue(Long::class.java)
    val messageType = snapshot.child("messageType").getValue(String::class.java)
    val chatType = snapshot.child("chatType").getValue(String::class.java)
//    val receiverId = snapshot.child("receiverId").children.associate {
//        it.key!! to it.getValue(String::class.java)!!
//    }
    return ChatMessage(
        senderId = senderId ?: "",
        chatRoomId = chatRoomId ?: "",
        senderName = senderName ?: "",
        receiverId = receiverId ?: "",
        patientId = patientId ?: "",
        message = message ?: "",
        timestamp = timestamp ?: 0,
        messageType = messageType ?: "",
        chatType = chatType ?: ""
    )
}