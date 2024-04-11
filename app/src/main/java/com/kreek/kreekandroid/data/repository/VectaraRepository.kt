package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryResponse

interface VectaraRepository {
    suspend fun sendQuery(body: VectaraQueryBody):  VectaraQueryResponse?
}