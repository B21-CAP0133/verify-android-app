package com.b21cap0133.verify.remotesource

import android.annotation.SuppressLint
import android.util.Log
import com.b21cap0133.verify.domain.RemoteDataInterface
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val retrofit: RemoteInterface): RemoteDataInterface {
    @SuppressLint("CheckResult")
    override fun getResult(content: String): Flowable<ApiResponse<UserDataEntity>> {
        val returnValue = PublishSubject.create<ApiResponse<UserDataEntity>>()
        val call = retrofit.fetchProfile(content)
        call
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                returnValue.onNext(if (response.username.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                returnValue.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return returnValue.toFlowable(BackpressureStrategy.BUFFER)
    }
}