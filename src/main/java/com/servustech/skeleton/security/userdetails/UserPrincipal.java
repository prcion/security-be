package com.servustech.skeleton.security.userdetails;

import com.servustech.skeleton.features.account.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Spring security's User Principal which contains User details
 */
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class UserPrincipal implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getAccountType().toString()));
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        var accountStatus = user.getAccountStatus();
        return accountStatus.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        var accountStatus = user.getAccountStatus();
        return !accountStatus.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        var accountStatus = user.getAccountStatus();
        return accountStatus.isActive();
    }
}
