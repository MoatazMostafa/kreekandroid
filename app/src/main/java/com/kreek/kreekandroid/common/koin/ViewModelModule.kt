package com.kreek.kreekandroid.common.koin

import android.os.Bundle
import com.kreek.kreekandroid.ui.features.authentication.AuthenticationViewModel
import com.kreek.kreekandroid.ui.features.chatroom.ChatRoomViewModel
import com.kreek.kreekandroid.ui.features.home.HomeViewModel
import com.kreek.kreekandroid.ui.features.host.MainViewModel
import com.kreek.kreekandroid.ui.features.patientinfo.PatientInfoViewModel
import com.kreek.kreekandroid.ui.features.selectdoctorchat.SelectDoctorChatViewModel
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
        SplashViewModel(
            application = androidApplication(),
            getCachedDoctorUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            application = androidApplication(),
            getCachedDoctorUseCase = get(),
            receiveChatRoomsInfoListUseCase = get(),
            receiveChatMessageUseCase = get(),
            cacheChatRoomMessagesListUseCase = get(),
            getCachedChatRoomMessagesListUseCase = get(),
            updateCachedChatRoomMessages = get()
        )
    }

    viewModel { (arguments: Bundle?) ->
        ChatRoomViewModel(
            application = androidApplication(),
            backStackEntryArguments = arguments,
            sendChatMessageUseCase = get(),
            receiveChatMessageUseCase = get(),
            getCachedChatRoomMessagesUseCase = get(),
            updateCachedChatRoomMessages = get(),
            getVectaraQueryResponseUseCase = get(),
            getCachedDoctorUseCase = get(),
        )
    }

    viewModel { (arguments: Bundle?) ->
        PatientInfoViewModel(
            application = androidApplication(),
            backStackEntryArguments = arguments
        )
    }

    viewModel {
        SelectDoctorChatViewModel(
            application = androidApplication(),
            getDoctorListUseCase = get(),
            sendChatRoomUseCase = get(),
            getCachedDoctorUseCase = get()
        )
    }

    viewModel {
        AuthenticationViewModel(
            application = androidApplication(),
            getDoctorByNumberUseCase = get(),
            setDoctorUseCase = get(),
            cacheDoctorUseCase = get()
        )
    }
}
