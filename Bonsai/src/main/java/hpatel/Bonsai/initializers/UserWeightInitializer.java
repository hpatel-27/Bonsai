package hpatel.Bonsai.initializers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import hpatel.Bonsai.models.UserWeight;
import hpatel.Bonsai.repositories.UserWeightRepository;

/**
 * Initializes weights into the database
 *
 * @author Harsh Patel
 *
 */
@Configuration
public class UserWeightInitializer {

    @Autowired
    private UserWeightRepository weightRepository;

    /**
     * Initialize user weight data into the database. Checks the database for
     * entries to this specific repository. If none, add some on program
     * startup.
     */
    @PostConstruct
    public void intializeWeights () {
        // check if there are no existing weights
        if ( weightRepository.count() == 0 ) {
            final UserWeight u1 = new UserWeight( 190.0 );
            final UserWeight u2 = new UserWeight( "2024-07-24", 175.0 );
            final UserWeight u3 = new UserWeight( 192.3 );

            weightRepository.save( u1 );
            weightRepository.save( u2 );
            weightRepository.save( u3 );

        }
    }
}
