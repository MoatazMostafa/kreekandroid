package com.kreek.kreekandroid.common.util

sealed class Resource<T>(
    val data: T?,
    val errorCode: String? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(errorCode: String, errorMessage: String? = null) :
        Resource<T>(errorCode = errorCode, errorMessage = errorMessage,data = null,)
}