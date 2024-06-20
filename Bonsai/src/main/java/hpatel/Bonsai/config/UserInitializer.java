package hpatel.Bonsai.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import hpatel.Bonsai.models.User;
import hpatel.Bonsai.repositories.UserRepository;

/**
 * Initializes Admin and Guest user's in the system.
 */
@Configuration
public class UserInitializer {
    /**
     * User repository
     */
    @Autowired
    private UserRepository  userRepository;

    /**
     * Password encoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Initializes the Admin user
     */
    @PostConstruct
    public void initializeAdminUser () {
        if ( userRepository.findByUsername( "admin" ) == null ) {
            final User adminUser = new User();
            adminUser.setUsername( "admin" );
            adminUser.setPassword( passwordEncoder.encode( "pw" ) );
            adminUser.setRole( "ADMIN" );
            userRepository.save( adminUser );
        }
    }

    /**
     * Initializes the guest user
     */
    @PostConstruct
    public void initializeGuestUser () {
        if ( userRepository.findByUsername( "guest" ) == null ) {
            final User guestUser = new User();
            guestUser.setUsername( "guest" );
            guestUser.setPassword( passwordEncoder.encode( "" ) );
            guestUser.setRole( "GUEST" );
            userRepository.save( guestUser );
        }
    }
}
