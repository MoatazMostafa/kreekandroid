package com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import kotlinx.coroutines.flow.Flow

interface GetChatRoomInfoByIdDataSource {
    operator fun invoke(userId: String, chatType: ChatType,chatRoomId:String): Flow<ChatRoomInfo>
}