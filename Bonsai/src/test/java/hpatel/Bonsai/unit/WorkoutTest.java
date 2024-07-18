package hpatel.Bonsai.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

/**
 * Class to test the workout class
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class WorkoutTest {
    /** Workout service object */
    @Autowired
    private WorkoutService workoutService;

    /** Deletes all preexisting workouts before each test */
    @BeforeEach
    public void setup () {
        workoutService.deleteAll();
    }

    @Test
    @Transactional
    public void testValidWorkout () {

        // create an object, save the object, and check that it matches
        final Workout w1 = new Workout( "Push" );

        assertEquals( "Push", w1.getName() );
        assertEquals( LocalDate.now().toString(), w1.getDate() );
        assertEquals( 0, w1.getExercises().size() );

    }
}
