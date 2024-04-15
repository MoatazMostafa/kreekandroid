package com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.chatmessage.model.mapSnapshotToChatRoomInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GetChatRoomInfoDataSourceImpl : GetChatRoomInfoDataSource {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val _chatRoomInfoListFlow = MutableSharedFlow<List<ChatRoomInfo>>()
    override fun invoke(userId: String, chatType: ChatType): Flow<List<ChatRoomInfo>> {
        val database = FirebaseDatabase.getInstance()
        val reference = if (chatType == ChatType.GROUP) {
            database.getReference("kreek_messages/chat_rooms_info/$userId/group_chat_room")
        } else {
            database.getReference("kreek_messages/chat_rooms_info/$userId/privet_chat_room")
        }
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val chatRoomInfoList = mutableListOf<ChatRoomInfo>()

                for (snapshot in dataSnapshot.children) {
                    val chatRoomInfo = mapSnapshotToChatRoomInfo(snapshot)
                    chatRoomInfoList.add(chatRoomInfo)
                }

                scope.launch {
                    _chatRoomInfoListFlow.emit(chatRoomInfoList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // TODO Handle possible errors.
            }
        }
        reference.addListenerForSingleValueEvent(listener)

        return _chatRoomInfoListFlow
    }
}