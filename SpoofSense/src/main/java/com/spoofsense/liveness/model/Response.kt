package com.spoofsense.liveness.model


import com.google.gson.annotations.SerializedName

internal data class Response(
    @SerializedName("detail")
    var detail: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("model_output")
    var modelOutput: ModelOutput? = null,
    @SerializedName("success")
    var success: Boolean? = null
) {
    internal data class ModelOutput(
        @SerializedName("pred_idx")
        var predIdx: String? = null,
        @SerializedName("pred_score")
        var predScore: String? = null,
        @SerializedName("prob_real")
        var probReal: Double? = null
    ){
        fun isReal() = predIdx.equals("real")
        fun isSpoof() = predIdx.equals("spoof")
    }
}