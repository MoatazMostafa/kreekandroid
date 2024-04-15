package com.kreek.kreekandroid.data.firebase.chatmessage.addchatroominfo

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo

interface AddChatRoomInfoDataSource {
    operator fun invoke(chatRoomInfo: ChatRoomInfo)
}