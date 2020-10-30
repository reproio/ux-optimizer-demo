package com.example.ux_optimizer_sample

import android.app.Application
import io.repro.android.Repro

class UxOptimizerSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Repro.setup(this, "<YOUR_REPRO_SDK_TOKEN>")
    }
}
