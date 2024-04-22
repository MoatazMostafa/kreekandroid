package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatRoomsInfoListUseCase {
    suspend operator fun invoke(userId: String, chatType: ChatType): Flow<List<ChatRoomInfo>>
}