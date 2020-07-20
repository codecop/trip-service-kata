package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedInUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        boolean isFriend = user.isFriend(loggedUser);

        List<Trip> tripList = new ArrayList<>();
        if (isFriend) {
            tripList = findTripsByUser(user);
        }
        return tripList;
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
