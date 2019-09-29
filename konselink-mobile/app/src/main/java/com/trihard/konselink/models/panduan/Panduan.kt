package com.trihard.konselink.models.panduan

import android.os.Parcelable
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlinx.android.parcel.Parcelize
import java.lang.IllegalArgumentException

@Parcelize
class Panduan(
    @SerializedName("ID")
    @Expose
    private val _id: Int,

    @SerializedName("title")
    @Expose
    private val _title: String?,

    @SerializedName("writer")
    @Expose
    private val _writer: String?,

    @SerializedName("thumbnail")
    @Expose
    private val _thumbnail: String?,

    @SerializedName("banner")
    @Expose
    private val _banner: String?,

    @SerializedName("content")
    @Expose
    private val _content: String?,

    @SerializedName("category")
    @Expose
    private val _category: List<String>?
): Parcelable{
    val title
        get() = _title ?: throw IllegalArgumentException("Title is required")

    val id
        get() = _id

    val writer
        get() = _writer ?: throw IllegalArgumentException("Writer name is required")

    val thumbnail
        get() = _thumbnail ?: throw IllegalArgumentException("Thumbnail is required")

    val banner
        get() = _banner ?: throw IllegalArgumentException("Banner is required")

    val content
        get() = _content ?: throw IllegalArgumentException("Content is required")

    val category
        get() = _category ?: throw IllegalArgumentException("Category is required")
}