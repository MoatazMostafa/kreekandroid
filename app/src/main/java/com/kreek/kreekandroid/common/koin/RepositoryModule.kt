package com.kreek.kreekandroid.common.koin


import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.data.repository.FirebaseRepositoryImpl
import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.data.repository.LocalCachedRepositoryImpl
import com.kreek.kreekandroid.data.repository.VectaraRepository
import com.kreek.kreekandroid.data.repository.VectaraRepositoryImpl
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val repositoryModule = module {

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(
            sendChatMessageDataSource = get(),
            receiveChatMessageDataSource = get(),
            setDoctorDataSource = get(),
            getDoctorDataSource = get(),
            addChatRoomInfoDataSource = get(),
            getChatRoomInfoDataSource = get(),
            getDoctorListDataSource = get(),
            getDoctorByNumberDataSource = get(),
            getChatRoomInfoByIdDataSource = get(),
            getPatientDataSource = get(),
            setPatientDataSource = get()
        )
    }

    single<LocalCachedRepository> {
        LocalCachedRepositoryImpl(
            localCachedDataSource = get()
        )
    }

    single<VectaraRepository> {
        VectaraRepositoryImpl()
    }
}