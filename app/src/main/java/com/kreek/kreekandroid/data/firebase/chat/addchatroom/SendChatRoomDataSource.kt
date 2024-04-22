package com.kreek.kreekandroid.data.firebase.chat.addchatroom

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo

interface SendChatRoomDataSource {
    operator fun invoke(chatRoomInfo: ChatRoomInfo)
}