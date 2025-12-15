package org.example.testareonline.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ActiveUsersService {
    private final Map<Integer, Set<String>> activeUsersPerTest = new ConcurrentHashMap<>();

    /**
     * Adds a user to the active list for a test.
     */
    public void addUserToTest(Integer testId, String username) {
        activeUsersPerTest.computeIfAbsent(testId, k -> ConcurrentHashMap.newKeySet()).add(username);
    }

    /**
     * Removes a user from the active list for a test.
     */
    public void removeUserFromTest(Integer testId, String username) {
        Set<String> users = activeUsersPerTest.get(testId);
        if (users != null) {
            users.remove(username);
            if (users.isEmpty()) {
                activeUsersPerTest.remove(testId);  // Cleanup empty sets
            }
        }
    }

    /**
     * Gets the set of active usernames for a test.
     */
    public Set<String> getActiveUsersForTest(Integer testId) {
        return activeUsersPerTest.getOrDefault(testId, Set.of());
    }

    /**
     * Checks if a username is already taken for a specific test.
     */
    public boolean isUsernameTaken(Integer testId, String username) {
        Set<String> users = activeUsersPerTest.get(testId);
        return users != null && users.contains(username);
    }
}