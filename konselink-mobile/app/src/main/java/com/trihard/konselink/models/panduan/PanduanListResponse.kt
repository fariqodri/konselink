package com.trihard.konselink.models.panduan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PanduanListResponse(
    @SerializedName("data")
    @Expose
    private val _panduans: List<Panduan>
) {
    val panduans
        get() = _panduans
}