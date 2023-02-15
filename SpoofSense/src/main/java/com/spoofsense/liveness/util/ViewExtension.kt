package com.spoofsense.liveness.util

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.widget.Button
import android.widget.TextView

fun TextView.set(text:String?,color: Int){
    setText(text)
    setTextColor(color)
}

fun Button.set(text: String?,textColor:Int,backgroundColor:Int){
    isAllCaps = false
    backgroundTintList = ColorStateList.valueOf(backgroundColor)
    setText(text)
    setTextColor(textColor)
}

fun Activity.startActivityWithExitAnim(intent: Intent){
    startActivity(intent)
    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
}