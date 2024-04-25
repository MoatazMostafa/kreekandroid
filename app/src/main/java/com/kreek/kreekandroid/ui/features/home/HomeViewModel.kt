package com.kreek.kreekandroid.ui.features.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.toDomainModel
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatRoomsInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.SendChatRoomUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.CacheChatRoomInfoListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.UpdateCachedChatRoomMessages
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.ui.features.home.model.HomeTab
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomInfoUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomMessagesUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toDomainModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    val sendChatRoomUseCase: SendChatRoomUseCase,
) : BaseViewModel(
    application = application
) {
    private val _groupChatRoomsList = MutableStateFlow<List<ChatRoomMessagesUIModel>>(emptyList())
    var groupChatRoomsList = _groupChatRoomsList.asStateFlow()
    private var fetchedGroupChatRoomsList: List<ChatRoomMessagesUIModel> = listOf()

    private val _privateChatRoomsList = MutableStateFlow<List<ChatRoomMessagesUIModel>>(emptyList())
    var privateChatRoomsList = _privateChatRoomsList.asStateFlow()
    private var fetchedPrivateChatRoomsList: List<ChatRoomMessagesUIModel> = listOf()

    private val _userDoctor = MutableStateFlow(DoctorUIModel())
    var userDoctor = _userDoctor.asStateFlow()

    var selectedTab = HomeTab.GROUP_CHATS
    val scope = CoroutineScope(Dispatchers.Default)

    init {
        viewModelScope.launch {
            _userDoctor.value = getCachedDoctorUseCase()?.toUIModel() ?: DoctorUIModel()
        }
        fetchChatRoomMessagesList()
    }

    private fun fetchChatRoomMessagesList() {
       viewModelScope.launch {
            receiveChatRoomsInfoListUseCase(
                userId = userDoctor.value.id,
                ChatType.PRIVATE
            ).collect { chatRoomInfoList ->
                if (chatRoomInfoList.isNotEmpty()) {
                    cacheChatRoomInfoListUseCase(chatRoomInfoList.map { it.toDomainModel() })
                    _privateChatRoomsList.value = getCachedChatRoomMessagesListUseCase().map { it.toUIModel() }
                    fetchedPrivateChatRoomsList = _privateChatRoomsList.value
                    _privateChatRoomsList.value.forEach { chatRoomMessages ->
                        viewModelScope.launch {
                            receiveChatMessageUseCase(
                                chatRoomMessages.chatRoomId,
                                chatRoomMessages.chatType,
                                chatRoomMessages.lastMessageTimestamp
                            ).collect { chatMessage ->
                                val cachedChatRoomMessages = getCachedChatRoomMessagesUseCase(chatMessage.first().chatRoomId)?.toUIModel()
                                updateCachedChatRoomMessages(
                                    chatRoomId = chatMessage.first().chatRoomId,
                                    lastMessage = chatMessage.lastOrNull()?.message,
                                    lastMessageTimestamp = chatMessage.lastOrNull()?.timestamp,
                                    numberOfUnreadMessages = (chatMessage.size + (cachedChatRoomMessages?.numberOfUnreadMessages
                                        ?: 0)),
                                    chatMessageList = chatMessage.map { it.toDomainModel() }
                                ).toUIModel()
                                _privateChatRoomsList.value = getCachedChatRoomMessagesListUseCase().map { it.toUIModel() }
                            }
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
                    _groupChatRoomsList.value.forEachIndexed { index, chatRoomMessages ->
                        receiveChatMessageUseCase(
                            chatRoomMessages.chatRoomId,
                            chatRoomMessages.chatType,
                            chatRoomMessages.lastMessageTimestamp
                        ).collect { chatMessage ->
                            val chatRoomMessagesList = _groupChatRoomsList.value.toMutableList()
                            val newChatRoomMessages = getCachedChatRoomMessagesUseCase(chatRoomMessages.chatRoomId)?.toUIModel()
                            chatRoomMessagesList[index] = updateCachedChatRoomMessages(
                                chatRoomId = chatRoomMessages.chatRoomId,
                                lastMessage = chatMessage.lastOrNull()?.message,
                                lastMessageTimestamp = chatMessage.lastOrNull()?.timestamp,
                                numberOfUnreadMessages = (chatMessage.size + (newChatRoomMessages?.numberOfUnreadMessages ?: 0)),
                                chatMessageList = chatMessage.map { it.toDomainModel() }
                            ).toUIModel()
                            _groupChatRoomsList.value = chatRoomMessagesList
                        }
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
        viewModelScope.launch {
            val chatRoomInfoUIModel = ChatRoomInfoUIModel(
                chatRoomId = "vectara_chat_bot_${_userDoctor.value.id}",
                chatType = ChatType.VECTARA_CHAT_BOT,
                firstUserId = _userDoctor.value.id,
                firstUserName = _userDoctor.value.name,
                secondUserId = "vectara_chat_bot",
                secondUserName = "Vectara Chat Bot",
            )
            cacheChatRoomInfoListUseCase(listOf(chatRoomInfoUIModel.toDomainModel()))
            sendChatRoomUseCase(chatRoomInfoUIModel.toDomainModel())
        }
        navController?.navigate(
            route =
            KreekNavDestination.ChatRoom.getNavigationRoute(
                chatRoomId = "vectara_chat_bot_${_userDoctor.value.id}",
                chatType = ChatType.VECTARA_CHAT_BOT.value
            ).route
        )
    }
}