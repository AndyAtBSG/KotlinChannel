package com.appt.kotlinchannel

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
// https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: ChannelViewModel by viewModels()

        findViewById<View>(R.id.pressMe).setOnClickListener {
            model.someonePressedTheButton()
        }

        lifecycleScope.launch {
            model.events.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    showDialog()
                }
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setMessage("Hello")
            .setPositiveButton("Okay") { _, _ -> }
            .setNegativeButton("No Way!") { _, _ -> }
            .create()
            .show()
    }
}