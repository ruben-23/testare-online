package org.example.testareonline.service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class GuestService {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int USERNAME_LENGTH = 8;  // Adjust length as needed

    /**
     * Generates a unique random username for guests.
     * Checks against the active users map to avoid duplicates.
     */
    public String generateUniqueRandomUsername(ActiveUsersService activeUsersService, Integer testId) {
        String username;
        do {
            byte[] bytes = new byte[USERNAME_LENGTH];
            SECURE_RANDOM.nextBytes(bytes);
            username = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, USERNAME_LENGTH);
        } while (activeUsersService.isUsernameTaken(testId, username));  // Ensure uniqueness per test
        return username;
    }
}