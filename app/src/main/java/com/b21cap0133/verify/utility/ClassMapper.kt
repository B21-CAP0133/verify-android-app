package com.b21cap0133.verify.utility

import com.b21cap0133.verify.domain.News
import com.b21cap0133.verify.domain.Request
import com.b21cap0133.verify.domain.UserData
import com.b21cap0133.verify.remotesource.NewsResponse
import com.b21cap0133.verify.remotesource.RequestEntity
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

    fun mapResponseToDomain(response: NewsResponse): News{
        return News(
            response.judul,
            response.linkBerita,
            response.tanggalBerita,
            response.berita
        )
    }

    fun mapDomainToEntity(domain: Request): RequestEntity{
        return RequestEntity(
            domain.message
        )
    }
}
