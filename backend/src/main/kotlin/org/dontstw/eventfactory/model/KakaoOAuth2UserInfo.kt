package org.dontstw.eventfactory.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import java.util.*

data class KakaoOAuth2UserInfo(
        @Transient
        override var attributes: Map<String, Any>,
        override var token: String
) : OAuth2UserInfo(attributes, token) {

    override fun setAttributes() {
        provider = "kakao"
        val properties: Map<String, Any> = attributes["properties"] as Map<String, Any>
        name = properties["nickname"] as String
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singleton(OAuth2UserAuthority("ROLE_ADMIN", getAttributes()))
    }
}