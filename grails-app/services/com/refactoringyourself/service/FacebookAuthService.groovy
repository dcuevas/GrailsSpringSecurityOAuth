package com.refactoringyourself.service

import com.refactoringyourself.User
import org.scribe.model.Token
import grails.converters.deep.JSON
import com.refactoringyourself.Role
import com.refactoringyourself.UserRole

class FacebookAuthService extends AuthService {

    static userInfoUrl = 'https://graph.facebook.com/me'

    static provider = 'facebook'

    User getUser(session) {
        Token token = getToken(session, provider)

        def jsonUser = getParsedResponse(token)

        return buildUserFromResponse(jsonUser)
    }

    private getParsedResponse(Token token) {
        def response = oauthService.getFacebookResource(token, userInfoUrl)
        return JSON.parse(response.body)
    }

    private buildUserFromResponse(def jsonUser) {
        User user = new User()
        user.oauthId = jsonUser.id
        user.avatarUrl = "http://graph.facebook.com/${jsonUser.id}/picture"
        user.username = jsonUser.username ?:"${jsonUser.first_name}.${jsonUser.last_name}"
        user.enabled = true
        user.oauthProvider = provider

        return user
    }
}
