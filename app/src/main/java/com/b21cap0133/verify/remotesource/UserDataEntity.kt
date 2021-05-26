package com.b21cap0133.verify.remotesource

import com.google.gson.annotations.SerializedName

data class UserDataEntity(
    @SerializedName("login")
    var username: String = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("location")
    var location: String? = "",
    @SerializedName("company")
    var company: String? = ""
)
