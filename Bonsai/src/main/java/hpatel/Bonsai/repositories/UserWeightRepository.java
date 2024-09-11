package hpatel.Bonsai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hpatel.Bonsai.models.UserWeight;

/**
 * UserWeightRepository is used to provide CRUD operations for the UserWeight
 * model, Spring will generate the appropriate code with JPA.
 *
 * @author Harsh Patel
 *
 */
public interface UserWeightRepository extends JpaRepository<UserWeight, Long> {

    /**
     * Collects all UserWeight objects with the provided weight. Spring will
     * generate code to make this happen.
     *
     * @param weight
     *            the weight
     * @return Found weight(s), null if none.
     */
    List<UserWeight> findByWeight ( double weight );

    /**
     * Finds a UserWeight object with the provided date. Spring will generate
     * code to make this happen.
     *
     * @param name
     *            Name of the UserWeight
     * @return Found UserWeight, null if none.
     */
    UserWeight findByDate ( String date );
}
