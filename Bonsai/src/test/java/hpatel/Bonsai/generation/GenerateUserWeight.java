package hpatel.Bonsai.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.UserWeight;
import hpatel.Bonsai.services.UserWeightService;

/**
 * Class to test the creation of the user's weight object
 *
 * @author Harsh Patel
 *
 */
@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
@ActiveProfiles ( "test" )
public class GenerateUserWeight {

    /**
     * The user weight service
     */
    @Autowired
    private UserWeightService userWeightService;

    /**
     * Test the creation of user weights
     */
    @Test
    @Transactional
    public void testUserWeight () {
        userWeightService.deleteAll();

        UserWeight uw1 = new UserWeight( 185.0 );

        userWeightService.save( uw1 );

        UserWeight uw2 = new UserWeight( 155.0 );

        userWeightService.save( uw2 );

        assertEquals( 2, userWeightService.count() );

        final List<UserWeight> weights = userWeightService.findAll();

        assertEquals( 2, weights.size() );

        uw1 = weights.get( 0 );
        uw2 = weights.get( 1 );

        final String testDate = LocalDate.now().toString();

        assertEquals( testDate, uw1.getDate() );
        assertEquals( 185.0, uw1.getWeight() );

        assertEquals( testDate, uw2.getDate() );
        assertEquals( 155.0, uw2.getWeight() );

    }

}
