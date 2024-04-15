package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetDoctorUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
): GetDoctorUseCase{
    override suspend operator fun invoke(id: String) = firebaseRepository.getDoctor((id)).toDomainModel()
}