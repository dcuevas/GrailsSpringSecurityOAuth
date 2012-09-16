package com.refactoringyourself.service

import grails.converters.deep.JSON

import org.scribe.model.Token
import com.refactoringyourself.User
import com.refactoringyourself.Role
import com.refactoringyourself.UserRole

class TwitterAuthService extends AuthService {

    static userInfoUrl = 'https://api.twitter.com/1.1/account/verify_credentials.json'

    static provider = 'twitter'

    User getUser(session) {
        Token token = getToken(session, provider)

        def jsonUser = getParsedResponse(token)

        return buildUserFromResponse(jsonUser)
    }

    private getParsedResponse(Token token) {
        def response = oauthService.getTwitterResource(token, userInfoUrl)
        return JSON.parse(response.body)
    }

    private buildUserFromResponse(def jsonUser) {
        User user = new User()
        user.oauthId = jsonUser.id
        user.avatarUrl = jsonUser.profile_image_url
        user.username = jsonUser.screen_name
        user.enabled = true
        user.oauthProvider = provider

        return user
    }
}
