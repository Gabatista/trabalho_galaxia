package com.cursoandroid.trabalho.repository.dto

import com.google.gson.annotations.SerializedName

data class FotoDTO(
    @SerializedName("copyright")
    var copyright: String ? = null,

    @SerializedName("date")
    var date: String ? = null,

    @SerializedName("explanation")
    var explanation: String ? = null,

    @SerializedName("hdurl")
    var hdurl: String? = null,

    @SerializedName("media_type")
    var media_type: String? = null,

    @SerializedName("service_version")
    var service_version: String ? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("url")
    var url: String? =null,

    val results: Array<FotoDTO>? = null

)
