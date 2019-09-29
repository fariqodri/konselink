package com.trihard.konselink.models.panduan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PanduanResponse(
    @SerializedName("article")
    @Expose
    private val _article: Panduan
) {
    val article
        get() = _article
}