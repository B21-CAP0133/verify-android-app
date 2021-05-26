package com.b21cap0133.verify.remotesource

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteInterface {
    @GET("/users/{user}")
    fun fetchProfile(@Path("user") username: String): Flowable<UserDataEntity>
}