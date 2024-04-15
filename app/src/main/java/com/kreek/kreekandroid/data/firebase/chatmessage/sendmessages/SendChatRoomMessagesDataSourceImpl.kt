package com.kreek.kreekandroid.data.firebase.chatmessage.sendmessages

import com.google.firebase.database.FirebaseDatabase
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType

//class SendChatRoomMessagesDataSourceImpl : SendChatMessageDataSource {
//    override suspend fun invoke(chatRoomMessages: ChatRoomMessages) {
//        val database = FirebaseDatabase.getInstance()
//        val reference = when(chatRoomMessages.chatType){
//            ChatType.GROUP.value -> database.getReference("kreek_messages/group_messages/")
//            ChatType.PRIVATE.value -> database.getReference("kreek_messages/direct_messages/")
//            ChatType.VECTARA_CHAT_BOT.value -> database.getReference("kreek_messages/vectara_chat_bot/")
//            else -> return
//        }
//        reference.child(chatRoomMessages.chatRoomId).setValue(chatRoomMessages)
//    }
//
//
//}