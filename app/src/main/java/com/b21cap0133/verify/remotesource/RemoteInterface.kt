package com.b21cap0133.verify.remotesource

import io.reactivex.Flowable
import retrofit2.http.*

interface RemoteInterface {
    @Headers("Content-Type:application/json")
    @POST("/search")
    fun fetchNews(@Body request: RequestEntity): Flowable<NewsResponse>

    /*@Headers("Content-Type:application/json")
    @GET("/search")
    fun testFetchNews(@Query("message")message: String): Flowable<NewsResponse>*/
}