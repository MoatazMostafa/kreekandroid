package com.kreek.kreekandroid.common.koin

import android.os.Bundle
import com.kreek.kreekandroid.ui.features.home.HomeViewModel
import com.kreek.kreekandroid.ui.features.host.MainViewModel
import com.kreek.kreekandroid.ui.features.chatroom.ChatRoomViewModel
import com.kreek.kreekandroid.ui.features.patientinfo.PatientInfoViewModel
import com.kreek.kreekandroid.ui.features.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * This property is used to create ViewModels and inject needed parameters
 * */
val viewModelModule = module {

    viewModel {
        MainViewModel(application = androidApplication())
    }


    viewModel {
        SplashViewModel(application = androidApplication())
    }

    viewModel {
        HomeViewModel(application = androidApplication())
    }

    viewModel { (arguments: Bundle?) ->
        ChatRoomViewModel(
            application = androidApplication(),
            backStackEntryArguments = arguments,
            sendChatMessageUseCase = get(),
            receiveChatMessageUseCase = get(),
            cacheLastMessageTimestampUseCase = get(),
            getCachedLastMessageTimestampUseCase = get(),
            getVectaraQueryResponseUseCase = get ()
        )
    }

    viewModel { (arguments: Bundle?) ->
        PatientInfoViewModel(
            application = androidApplication(),
            backStackEntryArguments = arguments
        )
    }
}
