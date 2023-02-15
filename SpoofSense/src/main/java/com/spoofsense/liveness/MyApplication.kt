package com.spoofsense.liveness

import android.app.Application

internal class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun context(): MyApplication {
            return instance as MyApplication
        }
    }

}