import unittest

from Trip import Trip
from TripService import TripService
from User import User
from UserNotLoggedInException import UserNotLoggedInException


class TripServiceTest(unittest.TestCase):
    def test_exception_when_not_logged_in(self):
        for_user = User()

        trip_service = TripService()
        try:
            trip_service._get_trips_pure(for_user,
                                         lambda: FakeUserSession(None),
                                         lambda user: None)
            self.fail("Should raise an exception")
        except UserNotLoggedInException:
            pass  # ok

    def test_list_no_trips_when_not_friend(self):
        logged_in_user = User()
        for_user = User()

        trip_service = TripService()
        trips = trip_service._get_trips_pure(for_user,  #
                                             lambda: FakeUserSession(logged_in_user),
                                             lambda user: None)

        self.assertEquals([], trips)

    def test_list_trips_when_friend(self):
        logged_in_user = User()
        for_user = User()
        for_user.add_friend(logged_in_user)
        users_trips = [Trip()]

        trip_service = TripService()
        trips = trip_service._get_trips_pure(for_user,  #
                                             lambda: FakeUserSession(logged_in_user),
                                             lambda user: users_trips)

        self.assertEquals(users_trips, trips)


class FakeUserSession(object):
    def __init__(self, logged_in_user):
        self.logged_in_user = logged_in_user

    def get_logged_user(self):
        return self.logged_in_user


if __name__ == '__main__':
    unittest.main()
