package com.kreek.kreekandroid.domain.usecases.patient

import com.kreek.kreekandroid.domain.model.PatientDomainModel

interface GetPatientUseCase {
    suspend operator fun invoke(patientId: String): PatientDomainModel
}