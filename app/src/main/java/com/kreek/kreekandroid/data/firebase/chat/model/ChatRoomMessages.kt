package com.kreek.kreekandroid.data.firebase.chat.model

data class ChatRoomMessages(
    var chatRoomId: String = "",
    var firstUserId: String = "",
    var secondUserId: String = "",
    var firstUserName:String = "",
    var secondUserName:String = "",
    var firstUserSpeciality: String = "",
    var secondUserSpeciality: String = "",
    var patientId: String = "",
    var patientName:String = "",
    var numberOfUnreadMessages: Int = 0,
    var chatType: String ="",
    var lastMessage: String? = "",
    var lastMessageTimestamp: Long = 0,
    var chatMessageList: MutableList<ChatMessage> = mutableListOf()
)
fun emptyChatRoomMessages() = ChatRoomMessages(
    chatRoomId = "",
    firstUserId = "",
    secondUserId = "",
    firstUserName = "",
    secondUserName = "",
    patientId = "",
    patientName = "",
    numberOfUnreadMessages = 0,
    chatType = "",
    lastMessage = "",
    lastMessageTimestamp = 0,
    chatMessageList = mutableListOf()
)