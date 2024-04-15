package com.kreek.kreekandroid.ui.features.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetDoctorChatDataListUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.ui.features.home.model.HomeTab
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatData
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toChatData
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    val getCachedDoctorUseCase: GetCachedDoctorUseCase,
    val getDoctorChatListUseCase: GetDoctorChatDataListUseCase
) : BaseViewModel(
    application = application
) {
    private val _groupChatRoomsList = MutableStateFlow<List<ChatData>>(emptyList())
    var groupChatRoomsList = _groupChatRoomsList.asStateFlow()
    private var fetchedGroupChatRoomsList: List<ChatData> = listOf()

    private val _privateChatRoomsList = MutableStateFlow<List<ChatData>>(emptyList())
    var privateChatRoomsList = _privateChatRoomsList.asStateFlow()
    private var fetchedPrivateChatRoomsList: List<ChatData> = listOf()

    private lateinit var userDoctor: DoctorUIModel

   var selectedTab = HomeTab.GROUP_CHATS

    init {
        viewModelScope.launch {
            userDoctor = getCachedDoctorUseCase()?.toUIModel() ?: DoctorUIModel()
            fetchPatientList()
        }
    }

    private suspend fun fetchPatientList() {
        getDoctorChatListUseCase(userId = userDoctor.id).collect { doctorChatList ->
            _privateChatRoomsList.value = doctorChatList.map { it.toChatData() }
            fetchedPrivateChatRoomsList = _privateChatRoomsList.value
        }
//        getUserChatRoomUseCase(userId = userDoctor.id, chatType = ChatType.GROUP).collect {
//            _privateChatRoomsList.value = it
//        }
    }

    fun onTabSelected(tab: HomeTab) {
        selectedTab = tab
    }

    fun onSearchTextChanged(searchText: String) {
        if (searchText.length > 3) {
            when (selectedTab) {
                HomeTab.GROUP_CHATS -> _groupChatRoomsList.value = fetchedGroupChatRoomsList.filter {
                    it.patientUIModel?.patientData?.name?.contains(
                        searchText,
                        ignoreCase = true
                    ) ?: false
                }

                HomeTab.PRIVATE_CHATS -> _privateChatRoomsList.value = fetchedPrivateChatRoomsList.filter {
                    it.doctorUIModel?.name?.contains(
                        searchText,
                        ignoreCase = true
                    ) ?: false
                }
            }
        }
    }

    fun onChatDataClick(chatData: ChatData) {
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(chatRoomId = chatData.chatRoomInfoDomainModel.chatRoomId).route
        )
    }

    fun onFloatingButtonClick() {
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(
                chatRoomId = "1",
                chatType = "chat_bot"
            ).route
        )
    }

}