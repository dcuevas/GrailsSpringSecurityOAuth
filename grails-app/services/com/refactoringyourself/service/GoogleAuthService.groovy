package com.refactoringyourself.service

import com.refactoringyourself.User
import org.scribe.model.Token
import grails.converters.deep.JSON

class GoogleAuthService extends AuthService {

    static userInfoUrl = 'https://www.googleapis.com/oauth2/v1/userinfo'

    static provider = 'google'

    User getUser(session) {
        Token token = getToken(session, provider)

        def jsonUser = getParsedResponse(token)

        return buildUserFromResponse(jsonUser)
    }

    private getParsedResponse(Token token) {
        def response = oauthService.getGoogleResource(token, userInfoUrl)
        return JSON.parse(response.body)
    }

    private buildUserFromResponse(def jsonUser) {
        User user = new User()
        user.oauthId = jsonUser.id
        user.avatarUrl = jsonUser.picture
        user.username = "${jsonUser.given_name}.${jsonUser.family_name}".toLowerCase()
        user.email = jsonUser.email
        user.enabled = true
        user.oauthProvider = provider

        return user
    }
}
