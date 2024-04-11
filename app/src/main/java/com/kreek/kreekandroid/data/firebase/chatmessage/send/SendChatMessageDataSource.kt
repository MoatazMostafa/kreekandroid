package com.kreek.kreekandroid.data.firebase.chatmessage.send

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage

interface SendChatMessageDataSource {
    suspend operator fun invoke(chatMessage: ChatMessage)
}