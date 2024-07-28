package hpatel.Bonsai.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.UserWeight;
import hpatel.Bonsai.services.UserWeightService;

/**
 * Class to test the user weight class
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class UserWeightTest {

    /** User Weight service object */
    @Autowired
    private UserWeightService service;

    @BeforeEach
    public void setup () {
        service.deleteAll();
    }

    @Test
    @Transactional
    public void testValidWeight () {

        final UserWeight uw1 = new UserWeight( 185.0 );

        final String testDate = LocalDate.now().toString();

        assertEquals( 185.0, uw1.getWeight() );
        assertEquals( testDate, uw1.getDate() );

        List<UserWeight> weights = service.findAll();

        assertEquals( 0, weights.size() );

        service.save( uw1 );

        weights = service.findAll();

        assertEquals( 1, weights.size() );

        UserWeight u1 = weights.get( 0 );

        assertEquals( testDate, u1.getDate() );
        assertEquals( 185.0, u1.getWeight() );

        final UserWeight uw2 = new UserWeight( 155.0 );

        assertEquals( testDate, uw2.getDate() );
        assertEquals( 155.0, uw2.getWeight() );

        weights = service.findAll();

        assertEquals( 1, weights.size() );

        service.save( uw2 );

        weights = service.findAll();

        assertEquals( 2, weights.size() );

        u1 = weights.get( 0 );
        UserWeight u2 = weights.get( 1 );

        assertEquals( testDate, u1.getDate() );
        assertEquals( 185.0, u1.getWeight() );

        assertEquals( testDate, u2.getDate() );
        assertEquals( 155.0, u2.getWeight() );

        service.delete( u1 );

        weights = service.findAll();

        assertEquals( 1, weights.size() );

        u2 = weights.get( 0 );

        assertEquals( testDate, u2.getDate() );
        assertEquals( 155.0, u2.getWeight() );

        service.save( u1 );
        weights = service.findAll();

        assertEquals( 2, weights.size() );

        service.deleteAll();
        weights = service.findAll();

        assertEquals( 0, weights.size() );

        service.save( u2 );

        final UserWeight foundByDate = service.findByDate( testDate );

        assertEquals( testDate, foundByDate.getDate() );
        assertEquals( 155.0, foundByDate.getWeight() );

    }

}
