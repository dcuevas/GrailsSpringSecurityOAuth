package com.refactoringyourself

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    String email
    String oauthId
    String oauthProvider
    String avatarUrl

	static constraints = {
		username blank: false, unique: true
		password blank: false, nullable: true
        email email: true, nullable: true
        avatarUrl nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

    static hasMany = [oAuthIDs: OAuthID]

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
