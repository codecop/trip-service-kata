package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.security.AuthorisationService;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

    private static final List<Trip> NO_TRIPS = Collections.emptyList();
    private final AuthorisationService authorisationService;
    private final TripDAO tripDAO;

    public TripService() {
        this(new AuthorisationService(), new TripDAO());
    }

    public TripService(AuthorisationService securityService, TripDAO tripDAO) {
        this.authorisationService = securityService;
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) {
        User loggedUser = authorisationService.getLoggedUser();

        boolean isFriend = user.isFriendWith(loggedUser);

        if (isFriend) {
            return tripDAO.findTripsByUser_(user);
        }
        return NO_TRIPS;
    }

}
