package hpatel.Bonsai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hpatel.Bonsai.models.User;

/**
 * UserRepository is used to allow finding specific users. Spring will generate
 * appropriate code with JPA.
 *
 * @author Harsh Patel
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User object with the provided name. Spring will generate code to
     * make this happen.
     *
     * @param username
     *            Name of the user
     * @return Found user, null if none.
     */
    User findByUsername ( String username );

    /**
     * Finds all User objects with the provided role
     *
     * @param role
     *            User role to filter by
     * @return List of User objects with roles matching that of the parameter.
     */
    List<User> findByRole ( String role );
}
