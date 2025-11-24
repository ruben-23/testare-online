package org.example.testareonline.entity;

public enum Role {
    USER("USER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    // convertire String -> Role
    public static Role fromString(String text) {
        if (text == null) {
            return null;
        }
        for (Role r : Role.values()) {
            if (r.roleName.equalsIgnoreCase(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Rol necunoscut: " + text);
    }

}
