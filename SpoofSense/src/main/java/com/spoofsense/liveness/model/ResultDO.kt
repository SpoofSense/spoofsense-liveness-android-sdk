package com.spoofsense.liveness.model

import com.spoofsense.liveness.util.GSONUtils

internal data class ResultDO(
    var message: String,
    var status: Boolean
) {
    fun toJson() = GSONUtils.toString(this)
}

