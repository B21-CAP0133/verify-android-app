package com.b21cap0133.verify.domain

data class News(
    val judul: String = "",
    val prediksi: String = "",
    val linkBerita: String = "",
    val tanggalBerita: String = "",
    val berita: String = "",
    val kategori: String? = "",
    val preview: String = ""
)
