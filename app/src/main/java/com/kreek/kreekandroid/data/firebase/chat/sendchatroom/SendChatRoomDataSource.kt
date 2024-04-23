package com.kreek.kreekandroid.data.firebase.chat.sendchatroom

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo

interface SendChatRoomDataSource {
    operator fun invoke(chatRoomInfo: ChatRoomInfo)
}