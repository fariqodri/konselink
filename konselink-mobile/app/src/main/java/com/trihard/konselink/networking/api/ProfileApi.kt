package com.trihard.konselink.networking.api

import com.trihard.konselink.models.profile.LoginBody
import com.trihard.konselink.models.profile.AccountResponse
import com.trihard.konselink.models.profile.RegisterBody
import com.trihard.konselink.models.profile.UpdateProfileBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileApi {
    @POST("users")
    fun register(@Body registerBody: RegisterBody): Call<AccountResponse>

    @POST("users/login")
    fun login(@Body loginBody: LoginBody): Call<AccountResponse>

    @GET("users")
    fun getUserData(@Header("Authorization") authToken: String): Call<AccountResponse>

    @PUT("users")
    fun updateProfile(@Header("Authorization") authToken: String, @Body updateProfileBody: UpdateProfileBody): Call<Call<AccountResponse>>
}