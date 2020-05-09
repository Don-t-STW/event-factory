package org.dontstw.eventfactory.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.oidc.OidcIdToken
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import java.util.*
import kotlin.collections.HashMap

@Table("users")
abstract class OAuth2UserInfo : OidcUser {
    @Id
    protected var id: Long? = null
    internal open var name: String = "Unknown"
    protected var provider: String = "Unknown"
    internal open var token: String = "Unknown"

    @Transient
    internal open var attributes: Map<String, Any> = HashMap()

    constructor() {}

    protected constructor(attr: Map<String, Any>, token: String) {
        this.attributes = attr
        this.token = token
        setAttributes()
    }

    abstract fun setAttributes()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singleton(OAuth2UserAuthority(getAttributes()))
    }

    override fun getAttributes(): Map<String, Any> {
        return this.attributes
    }

    override fun getUserInfo(): OidcUserInfo? {
        return null
    }

    override fun getName(): String {
        return name
    }

    override fun getIdToken(): OidcIdToken? {
        return null
    }

    override fun getClaims(): Map<String, Any> {
        return this.getAttributes()
    }
}
