package com.project.skinalyze.data.response

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
