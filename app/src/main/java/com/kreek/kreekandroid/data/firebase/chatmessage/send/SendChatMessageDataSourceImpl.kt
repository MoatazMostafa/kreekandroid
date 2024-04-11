package com.kreek.kreekandroid.data.firebase.chatmessage.send

import com.google.firebase.database.FirebaseDatabase
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType

class SendChatMessageDataSourceImpl : SendChatMessageDataSource {
    override suspend fun invoke(chatMessage: ChatMessage) {
        val database = FirebaseDatabase.getInstance()
        val reference = when(chatMessage.chatType){
            ChatType.GROUP.value -> database.getReference("kreek_messages/group_messages/${chatMessage.chatRoomId}")
            ChatType.PRIVATE.value -> database.getReference("kreek_messages/direct_messages/${chatMessage.chatRoomId}")
            else -> database.getReference("kreek_messages/direct_messages/${chatMessage.chatRoomId}")
        }

        // Generate a new child location using a unique key
        val key = reference.push().key
        if (key != null) {
            reference.child(key).setValue(chatMessage)
        }
    }


}