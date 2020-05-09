package org.dontstw.eventfactory.model

import org.springframework.data.annotation.Transient

data class GithubOAuth2UserInfo(
        @Transient
        override var attributes: Map<String, Any>,
        override var token: String
) : OAuth2UserInfo() {
    override fun setAttributes() {
        provider = "github"
        name = attributes["login"] as String
    }
}
