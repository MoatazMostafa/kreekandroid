package com.kreek.kreekandroid.data.firebase.chat.sendchatroom

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType

class SendChatRoomDataSourceImpl : SendChatRoomDataSource {
    override fun invoke(chatRoomInfo: ChatRoomInfo) {
        val database = FirebaseDatabase.getInstance()
        val referenceSender: DatabaseReference
        val referenceReceiver: DatabaseReference
        when (chatRoomInfo.chatType) {
            ChatType.GROUP.value -> {
                referenceSender =
                    database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.firstUserId}/group_chat_room")
                referenceReceiver =
                    database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.secondUserId}/group_chat_room")
                referenceSender.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
                referenceReceiver.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
            }
            ChatType.PRIVATE.value -> {
                referenceSender =
                    database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.firstUserId}/privet_chat_room")
                referenceReceiver =
                    database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.secondUserId}/privet_chat_room")
                referenceSender.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
                referenceReceiver.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)

            }
            ChatType.VECTARA_CHAT_BOT.value -> {
                referenceSender =
                    database.getReference("kreek_messages/chat_rooms_info/${chatRoomInfo.firstUserId}/vectara_chat_bot_room")
                referenceSender.child(chatRoomInfo.chatRoomId).setValue(chatRoomInfo)
            }
        }
    }
}