package com.spoofsense.liveness.network

import com.spoofsense.liveness.constants.URLConstant
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.model.Response
import retrofit2.http.*

internal interface API {

    @POST(URLConstant.ANTI_SPOOF)
    suspend fun antiSpoof(@Body data:Request):Response

}