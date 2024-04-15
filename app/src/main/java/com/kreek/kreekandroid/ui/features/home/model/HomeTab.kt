package com.kreek.kreekandroid.ui.features.home.model

enum class HomeTab(val index:Int) {
    GROUP_CHATS(0),
    PRIVATE_CHATS(1);

    companion object {
        fun fromIndex(index: Int): HomeTab {
            return when (index) {
                0 -> GROUP_CHATS
                1 -> PRIVATE_CHATS
                else -> throw IllegalArgumentException("Invalid index")
            }
        }
    }
}