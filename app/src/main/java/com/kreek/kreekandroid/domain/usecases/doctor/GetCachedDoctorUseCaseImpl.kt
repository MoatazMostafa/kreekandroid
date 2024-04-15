package com.kreek.kreekandroid.domain.usecases.doctor

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.DoctorDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetCachedDoctorUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
):GetCachedDoctorUseCase {
    override suspend fun invoke(): DoctorDomainModel? {
        return localCachedRepository.getCacheDoctor()?.toDomainModel()
    }
}