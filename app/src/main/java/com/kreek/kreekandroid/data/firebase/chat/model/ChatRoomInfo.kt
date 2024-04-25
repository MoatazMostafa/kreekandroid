package com.kreek.kreekandroid.data.firebase.chat.model

import com.google.firebase.database.DataSnapshot

data class ChatRoomInfo(
    val chatRoomId: String,
    var firstUserId: String = "",
    var secondUserId: String = "",
    var firstUserName: String = "",
    var secondUserName: String = "",
    var firstUserSpeciality: String = "",
    var secondUserSpeciality: String = "",
    var patientId: String = "",
    var patientName: String = "",
    val chatType: String = "",
)

fun mapSnapshotToChatRoomInfo(snapshot: DataSnapshot): ChatRoomInfo {
    val chatRoomId = snapshot.child("chatRoomId").getValue(String::class.java)
    val firstUserId = snapshot.child("firstUserId").getValue(String::class.java)
    val secondUserId = snapshot.child("secondUserId").getValue(String::class.java)
    val firstUserName = snapshot.child("firstUserName").getValue(String::class.java)
    val secondUserName = snapshot.child("secondUserName").getValue(String::class.java)
    val firstUserSpeciality= snapshot.child("firstUserSpeciality").getValue(String::class.java)
    val secondUserSpeciality= snapshot.child("secondUserSpeciality").getValue(String::class.java)
    val patientId = snapshot.child("patientId").getValue(String::class.java)
    val patientName = snapshot.child("patientName").getValue(String::class.java)
    val chatType = snapshot.child("chatType").getValue(String::class.java)
    return ChatRoomInfo(
        chatRoomId = chatRoomId ?: "",
        firstUserId = firstUserId ?: "",
        secondUserId = secondUserId ?: "",
        firstUserName = firstUserName ?: "",
        secondUserName = secondUserName ?: "",
        firstUserSpeciality = firstUserSpeciality ?: "",
        secondUserSpeciality = secondUserSpeciality ?: "",
        patientId = patientId ?: "",
        patientName = patientName ?: "",
        chatType = chatType ?: "",
    )
}

fun ChatRoomInfo.toChatRoomMessage(): ChatRoomMessages {
    return  ChatRoomMessages(
        chatRoomId = chatRoomId,
        chatType = chatType,
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