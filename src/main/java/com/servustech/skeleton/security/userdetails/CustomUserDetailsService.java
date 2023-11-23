package com.servustech.skeleton.security.userdetails;

import com.servustech.skeleton.exceptions.CustomException;
import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.account.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        checkForUnconfirmedAccount(user);

        return new UserPrincipal(user);
    }


    private void checkForUnconfirmedAccount(User user){

        if (user.isInactive())
            throw new CustomException("Account is inactive");
    }
}
