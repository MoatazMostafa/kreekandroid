package com.kreek.kreekandroid.common.koin


import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.addchatroominfo.AddChatRoomInfoDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.addchatroominfo.AddChatRoomInfoDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoByIdDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoByIdDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.receivemessages.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.receivemessages.ReceiveChatMessageDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chatmessage.sendmessages.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.sendmessages.SendChatMessageDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorByNumberDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorByNumberDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorListDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorListDataSourceImpl
import com.kreek.kreekandroid.data.firebase.doctor.set.SetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.set.SetDoctorDataSourceImpl
import com.kreek.kreekandroid.data.firebase.patient.get.GetPatientDataSource
import com.kreek.kreekandroid.data.firebase.patient.get.GetPatientDataSourceImpl
import com.kreek.kreekandroid.data.firebase.patient.set.SetPatientDataSource
import com.kreek.kreekandroid.data.firebase.patient.set.SetPatientDataSourceImpl
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

    single<SetDoctorDataSource> {
        SetDoctorDataSourceImpl()
    }

    single<GetDoctorDataSource> {
        GetDoctorDataSourceImpl()
    }

    single<GetDoctorByNumberDataSource> {
        GetDoctorByNumberDataSourceImpl()
    }

    single<GetDoctorListDataSource> {
        GetDoctorListDataSourceImpl()
    }

    single<AddChatRoomInfoDataSource> {
        AddChatRoomInfoDataSourceImpl()
    }

    single<GetChatRoomInfoDataSource> {
        GetChatRoomInfoDataSourceImpl()
    }

    single<GetChatRoomInfoByIdDataSource> {
        GetChatRoomInfoByIdDataSourceImpl()
    }

    single<GetPatientDataSource> {
        GetPatientDataSourceImpl()
    }

    single<SetPatientDataSource> {
        SetPatientDataSourceImpl()
    }
}
