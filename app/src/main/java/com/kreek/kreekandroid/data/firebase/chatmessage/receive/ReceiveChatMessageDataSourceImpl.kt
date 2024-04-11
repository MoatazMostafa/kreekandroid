package com.kreek.kreekandroid.data.firebase.chatmessage.receive

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.chatmessage.model.mapSnapshotToChatMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ReceiveChatMessageDataSourceImpl : ReceiveChatMessageDataSource {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val _messagesFlow = MutableSharedFlow<List<ChatMessage>>()
    override suspend fun invoke(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>> {
        val database = FirebaseDatabase.getInstance()
        val reference = when(chatType){
            ChatType.GROUP ->
                database.getReference("kreek_messages/group_messages/$chatRoomId")
            ChatType.PRIVATE ->
                database.getReference("kreek_messages/direct_messages/$chatRoomId")
        }
        val messageListener: ValueEventListener?
        var query = reference.orderByChild("timestamp").startAfter(lastMessageTimestamp.toDouble())
        var currentLastMessageTimestamp = lastMessageTimestamp
        messageListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages = mutableListOf<ChatMessage>()

                for (snapshot in dataSnapshot.children) {
                    val message = mapSnapshotToChatMessage(snapshot)
                    currentLastMessageTimestamp = message.timestamp
                    messages.add(message)
                }

                scope.launch {
                    if (messages.isNotEmpty()) {
                        _messagesFlow.emit(messages)
                    }
                }

                // Remove the old listener
                query.removeEventListener(this)

                // Create a new query with the updated currentTime
                query = reference.orderByChild("timestamp")
                    .startAfter(currentLastMessageTimestamp.toDouble())

                // Add the listener to the new query
                query.addListenerForSingleValueEvent(this)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to receive message: ${databaseError.toException()}")
            }
        }
        // Add the listener to the query instead of the reference
        query.addValueEventListener(messageListener)
        return _messagesFlow
    }

    fun cancel() {
        job.cancel()
    }
}