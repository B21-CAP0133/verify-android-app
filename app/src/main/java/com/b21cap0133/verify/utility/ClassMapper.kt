package com.b21cap0133.verify.utility

import com.b21cap0133.verify.domain.UserData
import com.b21cap0133.verify.remotesource.UserDataEntity

object ClassMapper{
    fun mapEntityToDomain(entity: UserDataEntity): UserData {
        return UserData(
            entity.username,
            entity.name,
            entity.location,
            entity.company
            )
    }
}
