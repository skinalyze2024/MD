package com.project.skinalyze.data.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

    @field:SerializedName("prediction_result")
    val predictionResult: PredictionResult,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class PredictionResult(

    @field:SerializedName("google_result")
    val googleResult: List<GoogleResultItem>? = null,

    @field:SerializedName("prediction")
    val prediction: String? = null
)