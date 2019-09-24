package com.trihard.konselink.networking.api

import com.trihard.konselink.models.panduan.PanduanListResponse
import com.trihard.konselink.models.panduan.PanduanResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface PanduanApi {
    @GET("articles")
    fun getPanduanList(): Call<PanduanListResponse>

    @GET("articles/{articleId}")
    fun getPanduan(@Path("articleId") articleId: Int): Call<PanduanResponse>
}