package com.example.seminar_management_system.presentation.screens.entrance.login.state

import com.google.gson.annotations.SerializedName

data class LoginState(var data : String,
                      var success : Boolean?,
                      val error_code: String
)
