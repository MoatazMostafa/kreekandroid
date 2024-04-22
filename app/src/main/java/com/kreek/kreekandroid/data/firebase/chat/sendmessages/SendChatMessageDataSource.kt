package com.kreek.kreekandroid.data.firebase.chat.sendmessages

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage

interface SendChatMessageDataSource {
    suspend operator fun invoke(chatMessage: ChatMessage)
}