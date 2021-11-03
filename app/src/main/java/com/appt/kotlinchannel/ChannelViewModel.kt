package com.appt.kotlinchannel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class MainEvents {
    object ShowDialog : MainEvents()
}

class ChannelViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {
    val _events = Channel<MainEvents>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun someonePressedTheButton() = viewModelScope.launch(dispatcher) {
        // Do some async stuff
        _events.send(MainEvents.ShowDialog)
    }
}