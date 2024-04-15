package com.kreek.kreekandroid.domain.usecases.patient

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.PatientDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class SetPatientUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : SetPatientUseCase {
    override suspend fun invoke(patient: PatientDomainModel) {
        firebaseRepository.setPatient(patient.toDataModel())
    }
}