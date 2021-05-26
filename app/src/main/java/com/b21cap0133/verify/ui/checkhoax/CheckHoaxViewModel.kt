package com.b21cap0133.verify.ui.checkhoax

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.b21cap0133.verify.domain.RepositoryInterface
import com.b21cap0133.verify.domain.UserData

class CheckHoaxViewModel(private val repository: RepositoryInterface): ViewModel() {
    fun getResult(content: String): LiveData<UserData> {
        return LiveDataReactiveStreams.fromPublisher(repository.getResult(content))
    }
}