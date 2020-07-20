package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User loggedInUser = new User();
    User forUser = new User();

    @Test
    void shouldFailWhenUserIsNotLoggedIn() {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return null;
            }
        };

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(forUser));
    }

    @Test
    void shouldOfferNoTripsIfUserIsNotAFriend() {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return loggedInUser;
            }
        };

        List<Trip> trips = tripService.getTripsByUser(forUser);
        assertTrue(trips.isEmpty());
    }

}
