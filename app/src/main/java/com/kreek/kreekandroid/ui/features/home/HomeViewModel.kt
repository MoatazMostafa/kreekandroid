package com.kreek.kreekandroid.ui.features.home

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.toDomainModel
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatRoomsInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.UpdateCachedChatRoomMessages
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.ui.features.home.model.HomeTab
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomMessagesUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    val getCachedDoctorUseCase: GetCachedDoctorUseCase,
    val receiveChatRoomsInfoListUseCase: ReceiveChatRoomsInfoListUseCase,
    val receiveChatMessageUseCase: ReceiveChatMessageUseCase,
    val cacheChatRoomInfoListUseCase: CacheChatRoomInfoListUseCase,
    val getCachedChatRoomMessagesListUseCase: GetCachedChatRoomMessagesListUseCase,
    val getCachedChatRoomMessagesUseCase: GetCachedChatRoomMessagesUseCase,
    val updateCachedChatRoomMessages: UpdateCachedChatRoomMessages,
) : BaseViewModel(
    application = application
), DefaultLifecycleObserver {
    private val _groupChatRoomsList = MutableStateFlow<List<ChatRoomMessagesUIModel>>(emptyList())
    var groupChatRoomsList = _groupChatRoomsList.asStateFlow()
    private var fetchedGroupChatRoomsList: List<ChatRoomMessagesUIModel> = listOf()

    private val _privateChatRoomsList = MutableStateFlow<List<ChatRoomMessagesUIModel>>(emptyList())
    var privateChatRoomsList = _privateChatRoomsList.asStateFlow()
    private var fetchedPrivateChatRoomsList: List<ChatRoomMessagesUIModel> = listOf()

    private val _userDoctor = MutableStateFlow(DoctorUIModel())
    var userDoctor = _userDoctor.asStateFlow()

    var selectedTab = HomeTab.GROUP_CHATS

    private suspend fun fetchChatRoomMessagesList() {
        receiveChatRoomsInfoListUseCase(
            userId = userDoctor.value.id,
            ChatType.PRIVATE
        ).collect { chatRoomInfoList ->
            if (chatRoomInfoList.isNotEmpty()) {
                cacheChatRoomInfoListUseCase(chatRoomInfoList.map { it.toDomainModel() })
                _privateChatRoomsList.value =
                    getCachedChatRoomMessagesListUseCase().map { it.toUIModel() }
                fetchedPrivateChatRoomsList = _privateChatRoomsList.value
                _privateChatRoomsList.value.forEachIndexed { index, chatRoomMessages ->
                    receiveChatMessageUseCase(
                        chatRoomMessages.chatRoomId,
                        chatRoomMessages.chatType,
                        chatRoomMessages.lastMessageTimestamp
                    ).collect { chatMessage ->
                        val chatRoomMessagesList = _privateChatRoomsList.value.toMutableList()
                        val newChatRoomMessages = getCachedChatRoomMessagesUseCase(chatRoomMessages.chatRoomId)?.toUIModel()
                        chatRoomMessagesList[index] = updateCachedChatRoomMessages(
                            chatRoomId = chatRoomMessages.chatRoomId,
                            lastMessage = chatMessage.lastOrNull()?.message,
                            lastMessageTimestamp = chatMessage.lastOrNull()?.timestamp,
                            numberOfUnreadMessages = (chatMessage.size + (newChatRoomMessages?.numberOfUnreadMessages ?: 0)),
                            chatMessageList = chatMessage.map { it.toDomainModel() }
                        ).toUIModel()
                        _privateChatRoomsList.value = chatRoomMessagesList
                    }
                }
            }
        }

        receiveChatRoomsInfoListUseCase(
            userId = userDoctor.value.id,
            ChatType.GROUP
        ).collect { chatRoomInfoList ->
            if (chatRoomInfoList.isNotEmpty()) {
                cacheChatRoomInfoListUseCase(chatRoomInfoList.map { it.toDomainModel() })
                _groupChatRoomsList.value =
                    getCachedChatRoomMessagesListUseCase().map { it.toUIModel() }
                fetchedGroupChatRoomsList = _groupChatRoomsList.value
                _groupChatRoomsList.value.forEachIndexed { index, chatRoomMessage ->
                    receiveChatMessageUseCase(
                        chatRoomMessage.chatRoomId,
                        chatRoomMessage.chatType,
                        chatRoomMessage.lastMessageTimestamp
                    ).collect { chatMessage ->
                        val chatRoomMessagesList = _groupChatRoomsList.value.toMutableList()
                        chatRoomMessagesList[index] = updateCachedChatRoomMessages(
                            chatRoomId = chatRoomMessagesList[index].chatRoomId,
                            lastMessage = chatMessage.lastOrNull()?.message,
                            lastMessageTimestamp = chatMessage.lastOrNull()?.timestamp,
                            numberOfUnreadMessages = chatRoomMessagesList[index].numberOfUnreadMessages + chatMessage.size,
                            chatMessageList = chatMessage.map { it.toDomainModel() }
                        ).toUIModel()
                        _groupChatRoomsList.value = chatRoomMessagesList
                    }
                }
            }
        }
    }

    fun onTabSelected(tab: HomeTab) {
        selectedTab = tab
    }

    fun onSearchTextChanged(searchText: String) {
        if (searchText.length > 3) {
            when (selectedTab) {
                HomeTab.GROUP_CHATS -> _groupChatRoomsList.value =
                    fetchedGroupChatRoomsList.filter {
                        it.patientName.contains(
                            searchText,
                            ignoreCase = true
                        )
                    }

                HomeTab.PRIVATE_CHATS -> _privateChatRoomsList.value =
                    fetchedPrivateChatRoomsList.filter {
                        val receiverName = if (userDoctor.value.id == it.firstUserId)
                            it.secondUserName
                        else
                            it.firstUserName
                        receiverName.contains(
                            searchText,
                            ignoreCase = true
                        )
                    }
            }
        }
    }

    fun onChatDataClick(chatData: ChatRoomMessagesUIModel) {
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(chatRoomId = chatData.chatRoomId).route
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
    override fun onResume(owner: LifecycleOwner) {
        viewModelScope.launch {
            _userDoctor.value = getCachedDoctorUseCase()?.toUIModel() ?: DoctorUIModel()
            fetchChatRoomMessagesList()
        }
    }
}