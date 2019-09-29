package com.trihard.konselink.models.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountBody(
    @SerializedName("account")
    @Expose
    var data: User?
) {
    constructor(): this(null)
}