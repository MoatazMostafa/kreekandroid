package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.firebase.doctor.create.CreateDoctorDataSource
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CreateDoctorUseCaseImpl(
    private val createDoctorDataSource: CreateDoctorDataSource
) : CreateDoctorUseCase {
    override suspend fun invoke(doctor: DoctorDomainModel) {
        createDoctorDataSource(doctor.toDataModel())
    }
}