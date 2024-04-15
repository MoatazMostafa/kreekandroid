package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetDoctorListUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : GetDoctorListUseCase {
    override suspend operator fun invoke() = firebaseRepository.getDoctorList().map { it.toDomainModel() }
}