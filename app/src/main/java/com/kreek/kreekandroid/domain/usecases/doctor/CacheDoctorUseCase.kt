package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface CacheDoctorUseCase {
    suspend operator fun invoke(doctor: DoctorDomainModel)
}