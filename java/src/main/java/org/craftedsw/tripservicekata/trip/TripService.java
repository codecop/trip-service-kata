package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.security.AuthorisationService;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

    private final AuthorisationService authorisationService;

    public TripService() {
        this(new AuthorisationService());
    }

    public TripService(AuthorisationService securityService) {
        this.authorisationService = securityService;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = authorisationService.getLoggedUser();

        boolean isFriend = user.isFriend(loggedUser);

        List<Trip> tripList = new ArrayList<>();
        if (isFriend) {
            tripList = findTripsByUser(user);
        }
        return tripList;
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
