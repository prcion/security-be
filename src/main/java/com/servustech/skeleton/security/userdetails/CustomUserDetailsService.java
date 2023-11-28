package com.servustech.skeleton.security.userdetails;

import com.servustech.skeleton.exceptions.CustomException;
import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.account.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * creating custom user details service from spring security's UserDetailsService interface
 */
@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Get userName from database and create a user principal
     *
     * @return user principal details
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found."));

        checkForNotActiveAccount(user);

        return new UserPrincipal(user);
    }


    private void checkForNotActiveAccount(User user){
        var accountStatus = user.getAccountStatus();

        if (accountStatus.isInactive()) {
            throw new CustomException("Account is inactive");
        }

        if (accountStatus.isBanned()) {
            throw new CustomException("Account is banned");
        }

        if (accountStatus.isLocked()) {
            throw new CustomException("Account is locked");
        }

        if (accountStatus.isPending()) {
            throw new CustomException("Account is pending");
        }
    }
}
