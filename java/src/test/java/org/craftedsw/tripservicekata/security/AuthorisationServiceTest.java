package org.craftedsw.tripservicekata.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

class AuthorisationServiceTest {

    @Test
    void shouldFailWhenUserIsNotLoggedIn() {
        User notLoggedIn = null;
        AuthorisationService authorisationService = createAuthorisationService(notLoggedIn);

        assertThrows(UserNotLoggedInException.class, () -> authorisationService.getLoggedUser());
    }

    @Test
    void shouldReturnLoggedInUser() {
        User loggedIn = new User();
        AuthorisationService authorisationService = createAuthorisationService(loggedIn);

        User user = authorisationService.getLoggedUser();

        assertEquals(loggedIn, user);
    }

    private AuthorisationService createAuthorisationService(User stubbedLoggedInUser) {
        return new AuthorisationService() {
            @Override
            protected User getLoggedUserFromUserSession() {
                return stubbedLoggedInUser;
            }
        };
    }

}
