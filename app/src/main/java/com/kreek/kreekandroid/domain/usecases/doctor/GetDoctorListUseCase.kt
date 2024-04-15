package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

interface GetDoctorListUseCase {
    suspend operator fun invoke(): List<DoctorDomainModel>
}