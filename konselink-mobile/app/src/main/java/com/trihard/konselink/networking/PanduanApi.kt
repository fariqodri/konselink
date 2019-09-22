package com.trihard.konselink.networking

import com.trihard.konselink.models.panduan.PanduanListResponse
import retrofit2.http.GET
import retrofit2.Call

interface PanduanApi {
    @GET("articles")
    fun getPanduanList(): Call<PanduanListResponse>
}