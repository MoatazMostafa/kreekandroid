package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

data class ChatRoomMessagesUIModel(
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
    var chatMessageList: MutableList<ChatMessageUIModel> = mutableListOf()
)

fun ChatRoomMessagesDomainModel.toUIModel() = ChatRoomMessagesUIModel(
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
    chatType = chatType,
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp,
    chatMessageList = chatMessageList.map { it.toUIModel() }.toMutableList()
)

fun ChatRoomMessagesUIModel.toDomainModel() = ChatRoomMessagesDomainModel(
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
    chatType = chatType,
    lastMessage = lastMessage,
    lastMessageTimestamp = lastMessageTimestamp,
    chatMessageList = chatMessageList.map { it.toDomainModel() }.toMutableList()
)

fun ChatRoomMessages.toUIModel() = ChatRoomMessagesUIModel(
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
    chatMessageList = chatMessageList.map { it.toUIModel() }.toMutableList()
)
