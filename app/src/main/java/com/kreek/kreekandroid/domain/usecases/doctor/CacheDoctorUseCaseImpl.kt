package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CacheDoctorUseCaseImpl(
    private val localCachedDataSource: LocalCachedDataSource
): CacheDoctorUseCase{
    override suspend fun invoke(doctor: DoctorDomainModel) {
        return localCachedDataSource.cacheDoctor(doctor.toDataModel())
    }
}