package org.dontstw.eventfactory.oauth

import org.dontstw.eventfactory.repository.OAuth2UserInfoRepository
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class ReactOAuth2UserService : ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private var oAuth2UserInfoRepository: OAuth2UserInfoRepository

    constructor(oAuth2UserInfoRepository: OAuth2UserInfoRepository) {
        this.oAuth2UserInfoRepository = oAuth2UserInfoRepository
    }

    override fun loadUser(userRequest: OAuth2UserRequest?): Mono<OAuth2User> {
        val delegate = DefaultReactiveOAuth2UserService()
        val clientRegistrationId = userRequest!!.clientRegistration.registrationId
        val oAuth2User = delegate.loadUser(userRequest)
        val token = userRequest.accessToken

        return oAuth2User.flatMap { e: OAuth2User ->
            val oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(clientRegistrationId, e.attributes, token)
            oAuth2UserInfoRepository
                    .findByName(oAuth2UserInfo.name)
                    .switchIfEmpty(Mono.defer {
                        oAuth2UserInfoRepository.save(oAuth2UserInfo)
                    })
        }
    }
}
