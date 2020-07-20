package org.craftedsw.tripservicekata.security;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class AuthorisationService {

    public User getLoggedUser() {
        User loggedUser = getLoggedUserFromUserSession();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
        return loggedUser;
    }

    protected User getLoggedUserFromUserSession() {
        return UserSession.getInstance().getLoggedUser();
    }

}
