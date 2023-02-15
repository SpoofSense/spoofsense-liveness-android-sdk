package com.spoofsense.liveness.communicator

import com.spoofsense.liveness.util.GSONUtils

/**
 * Created by kushaal singla on 26-Aug-18.
 */
internal class DataSource<T> private constructor(var code: Int, val data: T?, var msg: String) {
    val isSuccess: Boolean
        get() = code == APIResponseCodeEnum.SUCCESS.key


    override fun toString(): String {
        return GSONUtils.toString(this)
    }

    companion object {
        fun <T> success(data: T?, msg: String = APIResponseCodeEnum.SUCCESS.msg): DataSource<T> {
            return DataSource(APIResponseCodeEnum.SUCCESS.key, data, msg)
        }

        fun <T> error(msg: String): DataSource<T> {
            return DataSource(APIResponseCodeEnum.FAILED.key, null, msg)
        }

        fun <T> error(codeEnum: APIResponseCodeEnum, msg: String): DataSource<T?> {
            return DataSource(codeEnum.key, null, msg)
        }

        fun <T> error(codeEnum: APIResponseCodeEnum): DataSource<T?> {
            return DataSource(codeEnum.key, null, codeEnum.msg)
        }
    }

}