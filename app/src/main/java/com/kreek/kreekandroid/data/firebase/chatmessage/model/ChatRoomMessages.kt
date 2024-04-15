package com.kreek.kreekandroid.data.firebase.chatmessage.model

data class ChatRoomMessages(
    val chatRoomId: String,
    val firstUserId: String,
    val secondUserId: String = "",
    val patientId: String = "",
    val firstUserName:String = "",
    val secondUserName:String = "",
    val patientName:String = "",
    val chatType: String,
    val lastMessage: String?,
    val lastMessageTimestamp: Long?,
    val chatMessageList: List<ChatMessage>
)
//fun mapSnapshotToChatRoomInfo(snapshot: DataSnapshot):ChatRoomInfo{
//    val userId = snapshot.child("userId").getValue(String::class.java)
//    val receiverId = snapshot.child("receiverId").getValue(String::class.java)
//    val patientId = snapshot.child("patientId").getValue(String::class.java)
//    val chatRoomId = snapshot.child("chatRoomId").getValue(String::class.java)
//    val chatType = snapshot.child("chatType").getValue(String::class.java)
//    val lastMessage = snapshot.child("lastMessage").getValue(String::class.java)
//    val lastMessageTimestamp = snapshot.child("lastMessageTimestamp").getValue(Long::class.java)
//    return ChatRoomInfo(
//        userId = userId ?: "",
//        receiverId = receiverId ?: "",
//        patientId = patientId ?: "",
//        chatRoomId = chatRoomId ?: "",
//        chatType = chatType ?: "",
//        lastMessage = lastMessage ?: "",
//        lastMessageTimestamp = lastMessageTimestamp ?: 0
//    )
//}