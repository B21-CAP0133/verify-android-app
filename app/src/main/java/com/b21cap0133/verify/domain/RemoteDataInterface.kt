package com.b21cap0133.verify.domain

import com.b21cap0133.verify.remotesource.ApiResponse
import com.b21cap0133.verify.remotesource.UserDataEntity
import io.reactivex.Flowable

interface RemoteDataInterface {
    fun getResult(content: String): Flowable<ApiResponse<UserDataEntity>>
}