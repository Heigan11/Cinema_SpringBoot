package edu.school21.cinema.models;

//    USER(new HashSet<Permission>(Arrays.asList(Permission.USERS_READ)),
//    ADMIN(new HashSet<Permission>(Arrays.asList(Permission.USERS_READ, Permission.USERS_WRITE));

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(new HashSet<Permission>(Arrays.asList(Permission.USERS_READ))),
    ADMIN(new HashSet<Permission>(Arrays.asList(Permission.USERS_READ, Permission.USERS_WRITE)));

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
