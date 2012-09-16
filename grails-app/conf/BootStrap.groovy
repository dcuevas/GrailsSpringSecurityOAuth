import com.refactoringyourself.Role
import com.refactoringyourself.User
import com.refactoringyourself.UserRole

class BootStrap {

    def init = { servletContext ->
        new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        assert Role.count() == 2
    }

    def destroy = {
    }
}
