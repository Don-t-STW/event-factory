package org.dontstw.eventfactory.repository

import org.dontstw.eventfactory.model.OAuth2UserInfo
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
open interface OAuth2UserInfoRepository : R2dbcRepository<OAuth2UserInfo, String> {

    @Query("SELECT * FROM users WHERE name = :name")
    fun findByName(name: String): Mono<OAuth2UserInfo>

    @Query("INSERT INTO users (name) VALUES (:name)")
    fun saveByName(name: String): Mono<OAuth2UserInfo>
}
