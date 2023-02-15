package com.spoofsense.liveness.network

import com.spoofsense.liveness.MyApplication
import com.spoofsense.liveness.R
import com.spoofsense.liveness.communicator.DataSource
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.repo.DataProvider
import retrofit2.Response
import java.io.IOException

internal object NetworkHelper : DataProvider {

    private fun <T> sendError(msg: String): DataSource<T> {
        return DataSource.error(msg)
    }

    private fun <T> sendError(e: Exception): DataSource<T> {
        return when (e) {
            is IOException -> sendError(getString(R.string.no_internet))
            else -> sendError(getString(R.string.server_error))
        }
    }

    private fun <T> sendResponse(response: Response<DataSource<T>>): DataSource<T> {
        return if (response.isSuccessful) {
            response.body()?.let { body ->
                if (body.code == 200 && body.data != null) {
                    sendResponse(DataSource.success(body.data, body.msg))
                } else {
                    sendError(body.msg)
                }
            } ?: sendError(getString(R.string.server_error))
        } else {
            sendError(getString(R.string.server_error))
        }
    }

    private fun <T> sendResponse(data: DataSource<T>?): DataSource<T> {
        return if (data?.isSuccess == true) {
            DataSource.success(data.data, data.msg)
        } else {
            DataSource.error(data?.msg ?: getString(R.string.data_error))
        }
    }

    private fun getString(resId: Int): String {
        return MyApplication.context().getString(resId)
    }

    override suspend fun antiSpoof(data: Request): DataSource<com.spoofsense.liveness.model.Response> {
        return try {
            val response = APIInstance.api.antiSpoof(data)
            sendResponse(DataSource.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            sendError(getString(R.string.server_error))
        }
    }


}