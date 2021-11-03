package com.appt.kotlinchannel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class MainEvents {
    object ShowDialog : MainEvents()
}

class ChannelViewModel : ViewModel() {
    private val _events = Channel<MainEvents>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun someonePressedTheButton() = viewModelScope.launch {
        // Do some async stuff
        _events.send(MainEvents.ShowDialog)
    }
}