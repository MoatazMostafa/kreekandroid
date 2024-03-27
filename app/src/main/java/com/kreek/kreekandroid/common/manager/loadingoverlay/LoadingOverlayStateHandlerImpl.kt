package com.kreek.kreekandroid.common.manager.loadingoverlay

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoadingOverlayStateHandlerImpl : LoadingOverlayStateHandler {
    private val _showLoading = MutableStateFlow(false)
    override val showLoading = _showLoading.asStateFlow()

    override fun setShowLoading(show: Boolean) {
        _showLoading.value = show
    }
}