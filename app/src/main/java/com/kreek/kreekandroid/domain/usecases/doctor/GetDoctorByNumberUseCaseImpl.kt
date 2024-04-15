package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetDoctorByNumberUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : GetDoctorByNumberUseCase {
    override suspend operator fun invoke(mobileNumber: String): DoctorDomainModel? {
        return firebaseRepository.getDoctorByNumber(mobileNumber)?.toDomainModel()
    }
}