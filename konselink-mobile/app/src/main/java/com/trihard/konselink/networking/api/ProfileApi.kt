package com.trihard.konselink.networking.api

import com.trihard.konselink.models.profile.LoginBody
import com.trihard.konselink.models.profile.AccountBody
import com.trihard.konselink.models.profile.RegisterBody
import com.trihard.konselink.models.profile.UpdateProfileBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileApi {
    @POST("users")
    fun register(@Body registerBody: RegisterBody): Call<AccountBody>

    @POST("users/login")
    fun login(@Body loginBody: LoginBody): Call<AccountBody>

    @GET("users")
    fun getUserData(@Header("Authorization") authToken: String): Call<AccountBody>

    @PUT("users")
    fun updateProfile(@Header("Authorization") authToken: String, @Body updateProfileBody: UpdateProfileBody): Call<Call<AccountBody>>
}