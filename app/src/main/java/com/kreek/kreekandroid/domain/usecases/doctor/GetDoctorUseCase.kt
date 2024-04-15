package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface GetDoctorUseCase {
    suspend operator fun invoke(id: String): DoctorDomainModel
}