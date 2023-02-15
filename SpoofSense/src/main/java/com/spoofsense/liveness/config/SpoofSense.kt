package com.spoofsense.liveness.config

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import com.spoofsense.liveness.activities.CameraActivity
import com.spoofsense.liveness.activities.GuidelinesActivity
import com.spoofsense.liveness.activities.SplashActivity
import com.spoofsense.liveness.enum.ResultEnum
import com.spoofsense.liveness.model.ResultDO

object SpoofSense {
    var onResult: ((result: String) -> Unit)? = null
    var apiKey = ""
    var appFirstName = "Spoof"
    var guidelinesButtonTextTitle = "Check Liveness"
    var splashButtonTextTitle = "Check Liveness"
    var buttonTitleColor = Color.parseColor("#FFFFFF")
    var buttonBackgroundColor = Color.parseColor("#0E68C0")
    var showSplashScreen = true
    var showFaceGuidelinesScreen = true
    var appLogo: Drawable? = null
    var appFirstNameColor = Color.parseColor("#0E68C0")
    var appLastNameColor = Color.parseColor("#222222")
    var appLastName = "Sense"
    var versionNumberString = "face v1.0.8"
    var versionNumberColor = Color.parseColor("#222222")


    fun launch(context: Context, onResult: ((result: String) -> Unit)) {
        this.onResult = onResult
        when {
            apiKey.isEmpty() -> {
                val resultDO = ResultDO(ResultEnum.API_KEY.resultMessage, false)
                this.onResult?.let { result -> result(resultDO.toJson()) }
            }
            showSplashScreen -> {
                context.startActivity(
                    SplashActivity.newInstance(context)
                )
            }
            showFaceGuidelinesScreen -> {
                context.startActivity(
                    GuidelinesActivity.newInstance(context)
                )
            }
            else -> {
                context.startActivity(
                    CameraActivity.newInstance(context)
                )
            }
        }
    }
}