package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CacheDoctorUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheDoctorUseCase{
    override suspend fun invoke(doctor: DoctorDomainModel) {
        return localCachedRepository.cacheDoctor(doctor.toDataModel())
    }
}