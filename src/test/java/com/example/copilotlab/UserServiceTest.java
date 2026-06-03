package com.example.copilotlab;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void returnsAllUsersWhenSearchIsBlank() {
        List<User> users = userService.findUsers("");

        assertEquals(3, users.size());
    }

    @Test
    void returnsAllUsersWhenSearchIsNull() {
        List<User> users = userService.findUsers(null);

        assertEquals(3, users.size());
    }

    @Test
    void findsUsersByNameCaseInsensitive() {
        List<User> users = userService.findUsers("ada");

        assertEquals(1, users.size());
        assertEquals("Ada Lovelace", users.get(0).name());
    }

    @Test
    void findsUsersByTeam() {
        List<User> users = userService.findUsers("platform");

        assertEquals(1, users.size());
        assertEquals("Grace Hopper", users.get(0).name());
    }

    @Test
    void returnsEmptyListWhenNoUsersMatch() {
        List<User> users = userService.findUsers("not-a-user");

        assertTrue(users.isEmpty());
    }

    @Test
    void findsUsersByRoleHappyPath() {
        List<User> users = userService.findUsers("Systems Developer");

        assertEquals(1, users.size());
        assertEquals("Linus Torvalds", users.get(0).name());
    }

    @Test
    void returnsAllUsersWhenSearchIsWhitespace() {
        List<User> users = userService.findUsers("   ");

        assertEquals(3, users.size());
    }

    @Test
    void findsUsersWithCaseInsensitiveSearch() {
        List<User> users = userService.findUsers("mOdErNiZaTiOn");

        assertEquals(1, users.size());
        assertEquals("Ada Lovelace", users.get(0).name());
    }
}
