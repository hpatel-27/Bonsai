package hpatel.Bonsai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hpatel.Bonsai.models.User;
import hpatel.Bonsai.repositories.UserRepository;

/**
 * Service used to handle the custom UserDetails class. Assists in retrieving
 * UserDetails objects from a given username.
 *
 * @author Harsh Patel
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * User repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Gets a user from the repository based on their username
     *
     * @param username
     *            Username of the user to load
     *
     * @return User with the matching username
     *
     * @throws UsernameNotFoundException
     *             if the user isn't found
     */
    @Override
    public UserDetails loadUserByUsername ( final String username ) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername( username );
        if ( user == null ) {
            throw new UsernameNotFoundException( "User not found with username: " + username );
        }

        return user;
    }
}
