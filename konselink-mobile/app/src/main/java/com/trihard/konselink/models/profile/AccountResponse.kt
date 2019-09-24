package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("account")
    @Expose
    val _data: User
)