package com.kreek.kreekandroid.ui.features.selectdoctorchat

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.AddChatRoomInfoUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorListUseCase
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomInfoUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toDomainModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectDoctorChatViewModel(
    application: Application,
    private val getDoctorListUseCase: GetDoctorListUseCase,
    private val addChatRoomInfoUseCase: AddChatRoomInfoUseCase,
    private val getCachedDoctorUseCase: GetCachedDoctorUseCase
) : BaseViewModel(
    application = application,
) {
    private val _doctorList = MutableStateFlow<List<DoctorUIModel>>(emptyList())
    var doctorList = _doctorList.asStateFlow()
    private var fetchedDoctorList: MutableList<DoctorUIModel> = mutableListOf()

    private lateinit var userDoctor: DoctorUIModel

    init {
        viewModelScope.launch {
            userDoctor = getCachedDoctorUseCase()?.toUIModel() ?: DoctorUIModel()
            fetchDoctorList()
        }
    }

    private suspend fun fetchDoctorList() {
        _doctorList.value = getDoctorListUseCase().map { it.toUIModel() }.also {
            fetchedDoctorList = it.toMutableList()
        }
    }

    fun onDoctorClicked(doctor: DoctorUIModel) {
        val chatRoomId = "DM_${userDoctor.id}_${doctor.id}"
        addChatRoomInfoUseCase(
            ChatRoomInfoUIModel(
                userId = userDoctor.id,
                receiverId = doctor.id,
                chatRoomId = chatRoomId,
                chatType = ChatType.PRIVATE,
                lastMessage = "",
                lastMessageTimestamp = 0
            ).toDomainModel()
        )
        navController?.popBackStack()
        navController?.navigate(
            route = KreekNavDestination.ChatRoom.getNavigationRoute(chatRoomId = chatRoomId).route
        )
    }

    fun onSearchTextChanged(searchText: String) {
        if (searchText.length < 3) {
            _doctorList.value = fetchedDoctorList
        } else {
            _doctorList.value =
                fetchedDoctorList.filter { it.name.contains(searchText, ignoreCase = true) }
        }
    }

}