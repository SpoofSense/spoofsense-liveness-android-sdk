package com.spoofsense.liveness.model

import com.spoofsense.liveness.util.GSONUtils

internal data class ResultDO(
    var message: String,
    var liveness: Boolean
) {
    fun toJson() = GSONUtils.toString(this)
}

