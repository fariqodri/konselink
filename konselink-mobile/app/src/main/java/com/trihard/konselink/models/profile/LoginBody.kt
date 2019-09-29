package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("email")
    @Expose
    val _email: String,

    @SerializedName("password")
    @Expose
    val _password: String
)