package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.domain.model.DoctorChatDataDomainModel
import kotlinx.coroutines.flow.Flow

interface GetDoctorChatDataListUseCase {
    suspend operator fun invoke(userId: String): Flow<List<DoctorChatDataDomainModel>>
}