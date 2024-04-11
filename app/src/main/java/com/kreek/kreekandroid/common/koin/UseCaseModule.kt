package com.kreek.kreekandroid.common.koin

import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.doctor.CreateDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.CreateDoctorUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCaseImpl
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCaseImpl
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

    factory<CreateDoctorUseCase> {
        CreateDoctorUseCaseImpl(get())
    }

    factory <GetVectaraQueryResponseUseCase>{
        GetVectaraQueryResponseUseCaseImpl(get())
    }
}
