package com.spoofsense.liveness.enum

internal enum class ResultEnum(val resultMessage: String) {
    REAL("Liveness Confirmed"),
    SPOOF("Please try again. Make sure the selfie is captured in proper lighting."),
    API_KEY("Api key is missing.")
}