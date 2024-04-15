package com.kreek.kreekandroid.common.koin

import com.kreek.kreekandroid.domain.usecases.chatmessage.CacheChatMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.CacheChatMessagesUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.GetCachedChatMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.GetCachedChatMessagesUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.AddChatRoomInfoUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.AddChatRoomInfoUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.CacheChatRoomInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.CacheChatRoomInfoListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetCachedChatRoomInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetCachedChatRoomInfoListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetChatDataByRoomIdUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetChatDataByRoomIdUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetDoctorChatDataDataListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetDoctorChatDataListUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.UpdateCachedChatRoomInfoUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.UpdateCachedChatRoomInfoUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.CacheDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.CacheDoctorUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorByNumberUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorByNumberUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorListUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.SetDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.SetDoctorUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.patient.GetPatientUseCase
import com.kreek.kreekandroid.domain.usecases.patient.GetPatientUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCase
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCaseImpl
import org.koin.dsl.module

/**
 * This property is used to create UseCases and inject needed parameters
 * */
val useCaseModule = module {
    factory<CacheLastMessageTimestampUseCase> {
        CacheLastMessageTimestampUseCaseImpl(get())
    }

    factory<GetCachedLastMessageTimestampUseCase> {
        GetCachedLastMessageTimestampUseCaseImpl(get())
    }

    factory<SendChatMessageUseCase> {
        SendChatMessageUseCaseImpl(get())
    }

    factory<ReceiveChatMessageUseCase> {
        ReceiveChatMessageUseCaseImpl(get())
    }

    factory<SetDoctorUseCase> {
        SetDoctorUseCaseImpl(get())
    }

    factory<GetVectaraQueryResponseUseCase> {
        GetVectaraQueryResponseUseCaseImpl(get())
    }

    factory<AddChatRoomInfoUseCase> {
        AddChatRoomInfoUseCaseImpl(get())
    }

    factory<GetDoctorChatDataListUseCase> {
        GetDoctorChatDataDataListUseCaseImpl(get())
    }

    factory<GetDoctorUseCase> {
        GetDoctorUseCaseImpl(get())
    }

    factory<GetDoctorByNumberUseCase> {
        GetDoctorByNumberUseCaseImpl(get())
    }

    factory<GetDoctorListUseCase> {
        GetDoctorListUseCaseImpl(get())
    }

    factory<GetCachedDoctorUseCase> {
        GetCachedDoctorUseCaseImpl(get())
    }

    factory<CacheDoctorUseCase> {
        CacheDoctorUseCaseImpl(get())
    }

    factory<GetChatDataByRoomIdUseCase> {
        GetChatDataByRoomIdUseCaseImpl(get())
    }

    factory<GetPatientUseCase> {
        GetPatientUseCaseImpl(get())
    }

    factory<CacheChatMessagesUseCase> {
        CacheChatMessagesUseCaseImpl(get())
    }

    factory<GetCachedChatMessagesUseCase> {
        GetCachedChatMessagesUseCaseImpl(get())
    }

    factory<CacheChatRoomInfoListUseCase> {
        CacheChatRoomInfoListUseCaseImpl(get())
    }

    factory<GetCachedChatRoomInfoListUseCase> {
        GetCachedChatRoomInfoListUseCaseImpl(get())
    }

    factory<UpdateCachedChatRoomInfoUseCase> {
        UpdateCachedChatRoomInfoUseCaseImpl(get())
    }
}
