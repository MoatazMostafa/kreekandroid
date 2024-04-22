package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType

data class ChatRoomMessagesDomainModel(
    var chatRoomId: String = "",
    var firstUserId: String = "",
    var secondUserId: String = "",
    var firstUserName: String = "",
    var secondUserName: String = "",
    var firstUserSpeciality: String = "",
    var secondUserSpeciality: String = "",
    var patientId: String = "",
    var patientName: String = "",
    var numberOfUnreadMessages: Int = 0,
    var chatType: ChatType = ChatType.PRIVATE,
    var lastMessage: String? = "",
    var lastMessageTimestamp: Long = 0,
    var chatMessageList: MutableList<ChatMessageDomainModel> = mutableListOf()
)

fun ChatRoomMessagesDomainModel.toDataModel() = ChatRoomMessages(
    chatRoomId = chatRoomId,
    firstUserId = firstUserId,
    secondUserId = secondUserId,
    firstUserName = firstUserName,
    secondUserName = secondUserName,
    firstUserSpeciality = firstUserSpeciality,
    secondUserSpeciality = secondUserSpeciality,
    patientId = patientId,
    patientName = patientName,
    numberOfUnreadMessages = numberOfUnreadMessages,
    chatType = chatType.value,
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp,
    chatMessageList = chatMessageList.map { it.toDataModel() }.toMutableList()
)

fun ChatRoomMessages.toDomainModel() = ChatRoomMessagesDomainModel(
    chatRoomId = chatRoomId,
    firstUserId = firstUserId,
    secondUserId = secondUserId,
    firstUserName = firstUserName,
    secondUserName = secondUserName,
    firstUserSpeciality = firstUserSpeciality,
    secondUserSpeciality = secondUserSpeciality,
    patientId = patientId,
    patientName = patientName,
    numberOfUnreadMessages = numberOfUnreadMessages,
    chatType = ChatType.fromString(chatType),
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp,
    chatMessageList = chatMessageList.map { it.toDomainModel() }.toMutableList()
)