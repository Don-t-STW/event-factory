package org.dontstw.eventfactory.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import java.util.*

data class GithubOAuth2UserInfo(
        val email: String = ""
) : OAuth2UserInfo() {
    constructor(attributes: Map<String, Any>) : this() {
        this.attributes = attributes
        this.provider = "github"
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
