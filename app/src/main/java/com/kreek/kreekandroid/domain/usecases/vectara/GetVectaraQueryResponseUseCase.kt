package com.kreek.kreekandroid.domain.usecases.vectara

import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryResponse

interface GetVectaraQueryResponseUseCase {
    suspend operator fun invoke(body: VectaraQueryBody): VectaraQueryResponse?
}