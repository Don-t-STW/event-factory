package org.dontstw.eventfactory.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import java.util.*
import kotlin.collections.HashMap

@Table("users")
abstract class OAuth2UserInfo() : OAuth2User {
    @Id
    internal var id: Long? = null
    internal var name: String = "Unknown"
    internal var provider: String = "Unknown"

    @Transient
    internal var attributes: Map<String, Any> = HashMap()

    constructor(attributes: Map<String, Any>) : this() {
        this.attributes = attributes
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
