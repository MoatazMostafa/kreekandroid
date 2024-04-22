package com.kreek.kreekandroid.data.firebase.chat.getchatroomsIds

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatRoomsInfoListDataSource {
    operator fun invoke(userId: String, chatType: ChatType): Flow<List<ChatRoomInfo>>
}