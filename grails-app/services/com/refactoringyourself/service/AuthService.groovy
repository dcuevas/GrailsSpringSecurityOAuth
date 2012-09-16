package com.refactoringyourself.service

import uk.co.desirableobjects.oauth.scribe.OauthService
import org.scribe.model.Token
import com.refactoringyourself.User
import com.refactoringyourself.security.SpringSecuritySigninService

abstract class AuthService {

    OauthService oauthService

    Token getToken(session, provider) {
        String sessionKey = oauthService.findSessionKeyForAccessToken(provider)
        Token token = session[sessionKey]

        return token
    }

    abstract User getUser(session)
}
