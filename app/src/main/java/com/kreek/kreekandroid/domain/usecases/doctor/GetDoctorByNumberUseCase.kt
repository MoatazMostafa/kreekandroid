package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface GetDoctorByNumberUseCase {
    suspend operator fun invoke(mobileNumber: String): DoctorDomainModel?
}