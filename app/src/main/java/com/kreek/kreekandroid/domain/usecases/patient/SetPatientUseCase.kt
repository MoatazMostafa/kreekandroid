package com.kreek.kreekandroid.domain.usecases.patient

import com.kreek.kreekandroid.domain.model.PatientDomainModel

interface SetPatientUseCase {
   suspend operator fun invoke(patient: PatientDomainModel)
}