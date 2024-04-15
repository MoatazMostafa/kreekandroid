package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.DoctorChatDataDomainModel
import kotlinx.coroutines.flow.Flow

class GetDoctorChatDataDataListUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : GetDoctorChatDataListUseCase {
    override suspend fun invoke(userId: String): Flow<List<DoctorChatDataDomainModel>> {
        return firebaseRepository.getDoctorChatDataList(userId)
    }
}