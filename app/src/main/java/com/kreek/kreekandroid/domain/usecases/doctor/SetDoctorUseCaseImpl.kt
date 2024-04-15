package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class SetDoctorUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : SetDoctorUseCase {
    override suspend fun invoke(doctor: DoctorDomainModel) {
        firebaseRepository.setDoctor(doctor.toDataModel())
    }
}