package com.spoofsense.liveness.repo

import com.spoofsense.liveness.communicator.DataSource
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.model.Response


internal interface DataProvider {
    suspend fun antiSpoof(data:Request): DataSource<Response>

}