package com.spoofsense.liveness.repo

import com.spoofsense.liveness.communicator.DataSource
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.model.Response
import com.spoofsense.liveness.network.NetworkHelper


/**
 * Data Provider is responsible for data source.
 * i.e. it will decide data need to fetch from local database or from remote server
 */
internal object DataProviderImp :  DataProvider {

    override suspend fun antiSpoof(data: Request): DataSource<Response> {
        return NetworkHelper.antiSpoof(data)
    }

}