package com.kreek.kreekandroid.data.vectara

import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface VectaraService {
    @POST("query")
    fun sendQuery(@Body body: VectaraQueryBody): Call<VectaraQueryResponse>
}