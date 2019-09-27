package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("ID")
    @Expose
    val _id: Int,

    @SerializedName("username")
    @Expose
    val _username: String,

    @SerializedName("email")
    @Expose
    val _email: String,

    @SerializedName("phoneNum")
    @Expose
    val _phoneNum: String,

    @SerializedName("password")
    @Expose(serialize = false)
    val _password: String,

    @SerializedName("community")
    @Expose
    val _community: String?,

    @SerializedName("token")
    @Expose(serialize = false)
    val _token: String,

    @SerializedName("role")
    @Expose
    val _role: String
)