package hpatel.Bonsai.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hpatel.Bonsai.models.UserWeight;
import hpatel.Bonsai.repositories.UserWeightRepository;

/**
 * The UserWeightService is used to handle CRUD operations on the UserWeight
 * model. In addition to all functionality from `Service`, we also have
 * functionality for retrieving a single UserWeight by name.
 *
 * @author Harsh Patel
 *
 */
@Component
@Transactional
public class UserWeightService extends Service<UserWeight, Long> {

    /**
     * UserWeightRepository, to be autowired in by Spring and provide CRUD
     * operations on UserWeight model.
     */
    @Autowired
    private UserWeightRepository userWeightRepository;

    @Override
    protected JpaRepository<UserWeight, Long> getRepository () {
        return userWeightRepository;
    }

    /**
     * Collects all UserWeight objects with the provided weight. Spring will
     * generate code to make this happen.
     *
     * @param weight
     *            the weight
     * @return Found weight(s), null if none.
     */
    public List<UserWeight> findByWeight ( final double weight ) {
        return userWeightRepository.findByWeight( weight );
    }

    /**
     * Finds a UserWeight object with the provided date. Spring will generate
     * code to make this happen.
     *
     * @param name
     *            Name of the UserWeight
     * @return Found UserWeight, null if none.
     */
    public UserWeight findByDate ( final String date ) {
        return userWeightRepository.findByDate( date );
    }
}
