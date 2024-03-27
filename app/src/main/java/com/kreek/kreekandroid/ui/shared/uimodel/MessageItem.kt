package com.kreek.kreekandroid.ui.shared.uimodel

data class MessageItem(
    val messageType:MessageType = MessageType.Text,
    val messageText:String = "",
    val messageSenderId: Int,
    val messageSenderNumber: Int,
    val messageSenderName: String = "",
    val messageDate:String
)

enum class MessageType{
    Text,
    Image,
    Voice,
    Video
}