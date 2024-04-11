package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface CreateDoctorUseCase {
   suspend operator fun invoke(doctor: DoctorDomainModel)
}