package com.refactoringyourself

import grails.plugins.springsecurity.Secured

class SecureController {

    @Secured(['ROLE_USER'])
    def index() {
        render 'Secure access only'
    }
}
