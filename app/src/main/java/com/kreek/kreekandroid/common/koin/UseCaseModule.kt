package com.kreek.kreekandroid.common.koin

import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatRoomsInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatRoomsInfoListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.SendChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.SendChatRoomUseCase
import com.kreek.kreekandroid.domain.usecases.chat.SendChatRoomUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomInfoListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomMessagesListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomMessagesListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListFlowUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListFlowUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chat.local.UpdateCachedChatRoomMessages
import com.kreek.kreekandroid.domain.usecases.chat.local.UpdateCachedChatRoomMessagesImpl
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
import com.kreek.kreekandroid.domain.usecases.patient.GetPatientUseCase
import com.kreek.kreekandroid.domain.usecases.patient.GetPatientUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCase
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCaseImpl
import org.koin.dsl.module

/**
 * This property is used to create UseCases and inject needed parameters
 * */
val useCaseModule = module {

    factory<SetDoctorUseCase> {
        SetDoctorUseCaseImpl(get())
    }

    factory<GetVectaraQueryResponseUseCase> {
        GetVectaraQueryResponseUseCaseImpl(get())
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

    factory<GetPatientUseCase> {
        GetPatientUseCaseImpl(get())
    }

    factory<SendChatMessageUseCase> {
        SendChatMessageUseCaseImpl(get())
    }

    factory<SendChatRoomUseCase> {
        SendChatRoomUseCaseImpl(get())
    }

    factory<ReceiveChatMessageUseCase> {
        ReceiveChatMessageUseCaseImpl(get())
    }

    factory<ReceiveChatRoomsInfoListUseCase> {
        ReceiveChatRoomsInfoListUseCaseImpl(get())
    }

    factory<UpdateCachedChatRoomMessages> {
        UpdateCachedChatRoomMessagesImpl(get())
    }

    factory<CacheChatRoomMessagesListUseCase> {
        CacheChatRoomMessagesListUseCaseImpl(get())
    }

    factory<CacheChatRoomInfoListUseCase> {
        CacheChatRoomInfoListUseCaseImpl(get())
    }

    factory<GetCachedChatRoomMessagesListUseCase> {
        GetCachedChatRoomMessagesListUseCaseImpl(get())
    }

    factory<GetCachedChatRoomMessagesUseCase> {
        GetCachedChatRoomMessagesUseCaseImpl(get())
    }

    factory<GetCachedChatRoomMessagesListFlowUseCase> {
        GetCachedChatRoomMessagesListFlowUseCaseImpl(get())
    }
}
