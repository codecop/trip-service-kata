package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.craftedsw.tripservicekata.security.AuthorisationService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User sandro = new User();
    List<Trip> sandrosTrips = Arrays.asList(new Trip());

    TripService tripService;

    @Test
    void shouldNotListTripsIfUserIsNotAFriend() {
        User notAFriend = new User();
        createTripServiceFor(notAFriend);

        List<Trip> trips = getSandrosTrips();

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldListTripsForFriend() {
        User aFriend = new User();
        sandro.addFriend(aFriend);
        createTripServiceFor(aFriend);

        List<Trip> trips = getSandrosTrips();

        assertEquals(sandrosTrips, trips);
    }

    @Test
    void shouldListTripsForFriendLaterInFriendList() {
        User aFriend = new User();
        sandro.addFriend(new User());
        sandro.addFriend(new User());
        sandro.addFriend(aFriend);
        createTripServiceFor(aFriend);

        List<Trip> trips = getSandrosTrips();

        assertEquals(sandrosTrips, trips);
    }

    private void createTripServiceFor(User stubbedLoggedInUser) {
        AuthorisationService authorisationService = new AuthorisationService() {
            // regular stub, could use Mockito
            @Override
            public User getLoggedUser() {
                return stubbedLoggedInUser;
            }
        };
        tripService = new TripService(authorisationService, null) {
            @Override
            public List<Trip> findTripsByUser_(User user) {
                if (user.equals(sandro)) {
                    return sandrosTrips;
                }
                Assertions.fail("expected user sandro " + sandro + ", got user " + user);
                return null;
            }
        };
    }

    private List<Trip> getSandrosTrips() {
        return tripService.getTripsByUser(sandro);
    }

}
