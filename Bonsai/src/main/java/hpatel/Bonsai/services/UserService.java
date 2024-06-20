package hpatel.Bonsai.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hpatel.Bonsai.models.User;
import hpatel.Bonsai.repositories.UserRepository;

/**
 * The UserService is used to handle CRUD operations on the User model. In
 * addition to all functionality from `Service`, we also have functionality for
 * retrieving a single User by name.
 *
 * @author Harsh Patel
 *
 */
@Component
@Transactional
public class UserService extends Service<User, Long> {

    /**
     * UserRepository, to be autowired in by Spring and provide CRUD operations
     * on User model.
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return userRepository;
    }

    /**
     * Find a user with the provided name
     *
     * @param username
     *            Username of the user to find
     * @return found user, null if none
     */
    public User findByName ( final String username ) {
        return userRepository.findByUsername( username );
    }

    /**
     * Validates a username and password, returning the user object if the
     * values are correct
     *
     * @param username
     *            Username to validate
     * @param pwd
     *            Password to validate
     * @return User object with given username and password
     */
    public User validateUser ( final String username, final String pwd ) {
        final User u = userRepository.findByUsername( username );
        if ( u != null && u.getPassword().equals( pwd ) ) {
            return u;
        }
        return null;
    }

    /**
     * Finds all User objects with the given role
     *
     * @param role
     *            User role to filter for
     * @return list of user objects with the given role
     */
    public List<User> findByRole ( final String role ) {
        return userRepository.findByRole( role );
    }
}
