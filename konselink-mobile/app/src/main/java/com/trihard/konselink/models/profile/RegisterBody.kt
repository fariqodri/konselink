package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterBody(
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
    @Expose
    val _password: String,

    @SerializedName("community")
    @Expose
    val _community: String
)
//{
//    val username
//        get() = _username
//
//    val email
//        get() = _email
//
//    val phoneNum
//        get() = _phoneNum
//
//    val password
//        get() = _password
//
//    val community
//        get() = _community
//}