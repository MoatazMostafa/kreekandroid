package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface GetCachedDoctorUseCase {
    suspend operator fun invoke(): DoctorDomainModel?
}