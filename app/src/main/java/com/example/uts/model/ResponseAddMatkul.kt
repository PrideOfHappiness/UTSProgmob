package com.example.uts.model

import com.google.gson.annotations.SerializedName

data class ResponseAddMatkul(
    @field:SerializedName("data")
    val data: List<ResponseItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)