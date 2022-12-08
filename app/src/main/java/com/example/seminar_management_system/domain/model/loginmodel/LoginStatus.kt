package com.example.seminar_management_system.domain.model.loginmodel

import com.google.gson.annotations.SerializedName

data class LoginStatus(
    @SerializedName("data" ) var data : String,
    @SerializedName("success" ) var success : Boolean,
    @SerializedName("code") val error_code: String
)
