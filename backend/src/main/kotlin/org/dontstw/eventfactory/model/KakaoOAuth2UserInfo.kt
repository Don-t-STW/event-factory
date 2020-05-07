package org.dontstw.eventfactory.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import java.util.*

data class KakaoOAuth2UserInfo(
        val email: String = ""
) : OAuth2UserInfo() {
    constructor(attributes: Map<String, Any>) : this() {
        this.attributes = attributes
        this.provider = "kakao"

        val properties: Map<String, Any> = attributes["properties"] as Map<String, Any>
        name = properties["nickname"] as String
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singleton(OAuth2UserAuthority(getAttributes()))
    }

    override fun getName(): String {
        return name
    }

    override fun getAttributes(): Map<String, Any> {
        return attributes
    }
}
