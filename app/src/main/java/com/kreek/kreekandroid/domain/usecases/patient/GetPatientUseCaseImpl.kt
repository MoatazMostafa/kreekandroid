package com.kreek.kreekandroid.domain.usecases.patient

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.PatientDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetPatientUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
): GetPatientUseCase{
    override suspend fun invoke(patientId: String): PatientDomainModel {
        return firebaseRepository.getPatient(patientId).toDomainModel()
    }
}