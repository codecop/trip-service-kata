package org.craftedsw.tripservicekata.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void shouldNotBeFriend() {
        User notAFriend = new User();
        User sandro = new User();

        assertFalse(sandro.isFriend(notAFriend));
    }

    @Test
    void shouldBeFirstFriend() {
        User aFriend = new User();
        User sandro = new User();
        sandro.addFriend(aFriend);

        assertTrue(sandro.isFriend(aFriend));
    }

    @Test
    void shouldBeFriendLaterInFriendList() {
        User aFriend = new User();
        User sandro = new User();
        sandro.addFriend(new User());
        sandro.addFriend(new User());
        sandro.addFriend(aFriend);

        assertTrue(sandro.isFriend(aFriend));
    }

}
