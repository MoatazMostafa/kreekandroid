package com.kreek.kreekandroid.common.manager.navigation

import androidx.navigation.NamedNavArgument

interface NavComposableDestination {
    val navComposableDestination: String
    fun getArguments(): List<NamedNavArgument> = emptyList()
}