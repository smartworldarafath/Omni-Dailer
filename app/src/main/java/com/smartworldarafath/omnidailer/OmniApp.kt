package com.smartworldarafath.omnidailer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OmniApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OmniApp)
            modules(appModule)
        }
    }
}
