package com.b21cap0133.verify.domain

import io.reactivex.Flowable

interface RepositoryInterface {
    fun getResult(content: String): Flowable<UserData>
}