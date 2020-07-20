package org.craftedsw.tripservicekata.security;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class AuthorisationService {

    public User getLoggedUser() {
        User loggedUser = getLoggedUserFromUserSession();
        validate(loggedUser);
        return loggedUser;
    }

    /* for test */ protected User getLoggedUserFromUserSession() {
        return UserSession.getInstance().getLoggedUser();
    }

    private void validate(User loggedUser) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }

}
