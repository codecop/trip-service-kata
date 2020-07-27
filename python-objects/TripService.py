from TripDAO import TripDAO
from UserNotLoggedInException import UserNotLoggedInException
from UserSession import UserSession


class TripService(object):
    def get_trips_by_user(self, user):

        def user_session_get_instance():
            return UserSession.get_instance()

        def trip_dao_find_trips(user):
            TripDAO.find_trips_by_user(user)

        self._foo(user, user_session_get_instance, trip_dao_find_trips)

    def _foo(self, user, user_session_get_instance, trip_dao_find_trips):
        logged_user = user_session_get_instance().get_logged_user()
        is_friend = False
        trip_list = []
        if logged_user:
            for friend in user.get_friends():
                if friend is logged_user:
                    is_friend = True
                    break
            if is_friend:
                trip_list = trip_dao_find_trips(user)
            return trip_list
        else:
            raise UserNotLoggedInException()
