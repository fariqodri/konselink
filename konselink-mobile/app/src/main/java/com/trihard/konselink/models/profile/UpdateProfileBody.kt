package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UpdateProfileBody(
    @SerializedName("username")
    @Expose
    val _username: String,

    @SerializedName("phoneNum")
    @Expose
    val _phoneNum: String,

    @SerializedName("community")
    @Expose
    val _community: String
)