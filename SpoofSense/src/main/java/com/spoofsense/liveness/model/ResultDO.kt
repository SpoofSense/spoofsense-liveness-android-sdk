package com.spoofsense.liveness.model

import com.spoofsense.liveness.util.GSONUtils

internal data class ResultDO(
    var message: String,
    var liveness: Boolean,
    var imgData: String,
) {
    fun toJson() = GSONUtils.toString(this)
}

