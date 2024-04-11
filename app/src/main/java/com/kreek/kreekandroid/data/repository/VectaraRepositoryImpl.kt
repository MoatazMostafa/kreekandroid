package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.vectara.VectaraRetrofitInstance
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VectaraRepositoryImpl : VectaraRepository{
    override suspend fun sendQuery(body: VectaraQueryBody): VectaraQueryResponse? {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val call = VectaraRetrofitInstance.vectaraService.sendQuery(body)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}