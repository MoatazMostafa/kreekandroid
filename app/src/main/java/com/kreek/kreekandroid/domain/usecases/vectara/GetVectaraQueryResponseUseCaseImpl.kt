package com.kreek.kreekandroid.domain.usecases.vectara

import com.kreek.kreekandroid.data.repository.VectaraRepository
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryResponse

class GetVectaraQueryResponseUseCaseImpl(private val vectaraRepository: VectaraRepository):GetVectaraQueryResponseUseCase {
    override suspend fun invoke(body: VectaraQueryBody): VectaraQueryResponse? {
        return vectaraRepository.sendQuery(body)
    }
}