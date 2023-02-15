package com.spoofsense.liveness.communicator

internal enum class APIResponseCodeEnum(val key: Int, val msg: String) {
    SUCCESS(200, "Success"),
    FAILED(400, "Failed"),
}
