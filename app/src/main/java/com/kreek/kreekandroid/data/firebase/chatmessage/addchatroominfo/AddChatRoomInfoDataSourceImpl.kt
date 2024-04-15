package com.kreek.kreekandroid.data.firebase.chatmessage.addchatroominfo

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType

class AddChatRoomInfoDataSourceImpl : AddChatRoomInfoDataSource {
    override fun invoke(chatRoomInfo: ChatRoomInfo) {
        val database = FirebaseDatabase.getInstance()
        val referenceSender:DatabaseReference
        val referenceReceiver:DatabaseReference
        if (chatRoomInfo.chatType == ChatType.GROUP.value) {
            referenceSender = database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.userId}/group_chat_room")
            referenceReceiver = database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.receiverId}/group_chat_room")
        } else {
            referenceSender = database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.userId}/privet_chat_room")
            referenceReceiver = database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.receiverId}/privet_chat_room")
        }
        referenceSender.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
        referenceReceiver.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
    }
}