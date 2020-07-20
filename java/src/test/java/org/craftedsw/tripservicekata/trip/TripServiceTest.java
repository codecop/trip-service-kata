package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User sandro = new User();
    TripService tripService;

    @Test
    void shouldFailWhenUserIsNotLoggedIn() {
        User notLoggedIn = null;
        createTripServiceFor(notLoggedIn);

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(sandro));
    }

    @Test
    void shouldNotListTripsIfUserIsNotAFriend() {
        User notAFriend = new User();
        createTripServiceFor(notAFriend);

        List<Trip> trips = tripService.getTripsByUser(sandro);

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldListTripsForFriend() {
        User currentUser = new User();
        sandro.addFriend(currentUser);
        List<Trip> tripsOfSandro = Arrays.asList(new Trip());
        createTripServiceWith(currentUser, tripsOfSandro);

        List<Trip> trips = tripService.getTripsByUser(sandro);

        assertEquals(tripsOfSandro, trips);
    }

    @Test
    void shouldListTripsForFriendLaterInList() {
        User currentUser = new User();
        sandro.addFriend(new User());
        sandro.addFriend(new User());
        sandro.addFriend(currentUser);
        List<Trip> tripsOfUser = Arrays.asList(new Trip());
        createTripServiceWith(currentUser, tripsOfUser);

        List<Trip> trips = tripService.getTripsByUser(sandro);

        assertEquals(tripsOfUser, trips);
    }

    private void createTripServiceFor(User stubbedLoggedInUser) {
        createTripServiceWith(stubbedLoggedInUser, Collections.emptyList());
    }

    private void createTripServiceWith(User stubbedLoggedInUser, List<Trip> stubbedTripsOfUser) {
        tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return stubbedLoggedInUser;
            }

            @Override
            protected List<Trip> findTripsByUser(User user) {
                if (user.equals(sandro)) {
                    return stubbedTripsOfUser;
                }
                Assertions.fail("expected user sandro " + sandro + ", got user " + user);
                return null;
            }
        };
    }

}
