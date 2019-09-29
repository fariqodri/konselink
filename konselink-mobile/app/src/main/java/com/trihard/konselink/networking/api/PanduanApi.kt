package com.trihard.konselink.networking.api

import com.trihard.konselink.models.panduan.PanduanListResponse
import com.trihard.konselink.models.panduan.PanduanResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Path

interface PanduanApi {
    @GET("articles")
    fun getPanduanList(@Header("Authorization") token: String): Call<PanduanListResponse>

    @GET("articles/{articleId}")
    fun getPanduan(@Header("Authorization") token: String, @Path("articleId") articleId: Int): Call<PanduanResponse>
}