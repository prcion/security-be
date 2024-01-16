package com.findork.preclinical.features.commons;

import com.findork.preclinical.features.account.domain.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public final class SecurityUtils {
    private SecurityUtils() {
    } //enforce non-instability

    public static Optional<User> getPrincipal() {
        return getContext().getAuthentication() instanceof AnonymousAuthenticationToken || getContext().getAuthentication() == null
                ? Optional.empty()
                : Optional.of(getAuthenticatedPrincipal());
    }

    /**
     * Should never be used in public apis or will throw NPE
     * Designed to improve code readability, but can introduce exceptions
     */
    public static User getAuthenticatedPrincipal() {
        return (User) Objects.requireNonNull(getContext().getAuthentication()).getPrincipal();
    }
}
