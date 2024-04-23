package com.kreek.kreekandroid.common.koin


import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chat.sendchatroom.SendChatRoomDataSource
import com.kreek.kreekandroid.data.firebase.chat.sendchatroom.SendChatRoomDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chat.receivechatroomslist.ReceiveChatRoomsListDataSource
import com.kreek.kreekandroid.data.firebase.chat.receivechatroomslist.ReceiveChatRoomsListDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chat.receivemessages.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chat.receivemessages.ReceiveChatMessageDataSourceImpl
import com.kreek.kreekandroid.data.firebase.chat.sendmessages.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chat.sendmessages.SendChatMessageDataSourceImpl
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

    single<SendChatRoomDataSource> {
        SendChatRoomDataSourceImpl()
    }

    single<ReceiveChatRoomsListDataSource> {
        ReceiveChatRoomsListDataSourceImpl()
    }

    single<GetPatientDataSource> {
        GetPatientDataSourceImpl()
    }

    single<SetPatientDataSource> {
        SetPatientDataSourceImpl()
    }
}
