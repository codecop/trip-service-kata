package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.security.AuthorisationService;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

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

        boolean isFriend = user.isFriend(loggedUser);

        List<Trip> tripList = new ArrayList<>();
        if (isFriend) {
            tripList = findTripsByUser_(user);
        }
        return tripList;
    }

    public List<Trip> findTripsByUser_(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
