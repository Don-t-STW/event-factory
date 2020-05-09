//package org.dontstw.eventfactory.oauth
//class OAuth2UserInfoFactory
package OAuth2UserInfoFactory

import org.dontstw.eventfactory.model.GithubOAuth2UserInfo
import org.dontstw.eventfactory.model.KakaoOAuth2UserInfo
import org.dontstw.eventfactory.model.OAuth2UserInfo
import org.springframework.security.oauth2.core.OAuth2AccessToken

fun getOAuth2UserInfo(registrationId: String, attributes: Map<String, Any>, token: OAuth2AccessToken): OAuth2UserInfo {
    if (registrationId.equals("kakao", ignoreCase = true)) {
        return KakaoOAuth2UserInfo(attributes, token.tokenValue)
    } else if (registrationId.equals("github", ignoreCase = true)) {
        return GithubOAuth2UserInfo(attributes, token.tokenValue)
    } else {
        throw UnsupportedOperationException("$registrationId not support")
    }
}
