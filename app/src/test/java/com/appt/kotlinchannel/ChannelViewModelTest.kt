package com.appt.kotlinchannel

import com.jigtalk.sharedtest.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ChannelViewModelTest {
    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private val viewModel = ChannelViewModel(coroutineScopeRule.dispatcher)

    @Test
    fun shouldEmitDialogEvent_whenUserPressesButton() = runBlockingTest {
        var firedEvents: MainEvents? = null

        launch {
            firedEvents = viewModel.events.first()
        }

        assertNull(firedEvents)

        viewModel.someonePressedTheButton()

        assertEquals(MainEvents.ShowDialog, firedEvents)
    }
}