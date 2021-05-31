package com.b21cap0133.verify.remotesource

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("judul")
    val judul: String = "",
    @SerializedName("url")
    val linkBerita: String = "",
    @SerializedName("tanggal")
    val tanggalBerita: String = "",
    @SerializedName("isi")
    val berita: String = ""
)
