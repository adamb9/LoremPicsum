package com.example.lorempicsum

import android.app.Application
import com.example.lorempicsum.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LoremPicsumApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LoremPicsumApplication)
            modules(
                appModule
            )
        }
    }
}