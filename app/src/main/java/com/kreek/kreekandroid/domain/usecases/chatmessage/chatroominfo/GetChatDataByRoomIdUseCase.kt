package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import kotlinx.coroutines.flow.Flow

interface GetChatDataByRoomIdUseCase {
    suspend operator fun invoke(
        userId: String,
        chatType: ChatType,
        chatRoomId: String
    ): Flow<ChatRoomInfo>
}