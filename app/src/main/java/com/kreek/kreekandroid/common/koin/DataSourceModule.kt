package com.kreek.kreekandroid.common.koin


import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.receive.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.receive.ReceiveChatMessageDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.send.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.send.SendChatMessageDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.create.CreateDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.create.CreateDoctorDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSourceImpl
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val dataSourceModule = module {

    single<SendChatMessageDataSource> {
        SendChatMessageDataSourceImpl()
    }

    single<ReceiveChatMessageDataSource> {
        ReceiveChatMessageDataSourceImpl()
    }

    single<LocalCachedDataSource> {
        LocalCachedDataSourceImpl(context = get())
    }

    single<CreateDoctorDataSource> {
        CreateDoctorDataSourceImpl()
    }

    single<GetDoctorDataSource> {
        GetDoctorDataSourceImpl()
    }
}
