package com.kreek.kreekandroid.data.vectara

import com.kreek.kreekandroid.common.util.Constants.VECTARA_API_KEY
import com.kreek.kreekandroid.common.util.Constants.VECTARA_BASE_URL
import com.kreek.kreekandroid.common.util.Constants.VECTARA_CUSTOMER_ID
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object VectaraRetrofitInstance {
    private const val BASE_URL = VECTARA_BASE_URL
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("customer-id", VECTARA_CUSTOMER_ID)
                .addHeader("x-api-key", VECTARA_API_KEY)
                .build()
            chain.proceed(request)
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val vectaraService: VectaraService = retrofit.create(VectaraService::class.java)
}