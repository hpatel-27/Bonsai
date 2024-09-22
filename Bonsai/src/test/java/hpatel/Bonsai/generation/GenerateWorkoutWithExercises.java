package hpatel.Bonsai.generation;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.DomainObject;
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
@Transactional
@ActiveProfiles ( "test" )
public class GenerateWorkoutWithExercises {

    /**
     * The workout service
     */
    @Autowired
    private WorkoutService workoutService;

    /**
     * Deletes all the workouts before each method
     */
    @BeforeEach
    public void setup () {
        workoutService.deleteAll();
    }

    /**
     * Creates the workout for testing
     */
    @Test
    @Transactional
    public void createWorkout () {
        final Workout w1 = new Workout();
        w1.setName( "Push" );

        w1.addExercise( new Exercise( "Bench Press", "Chest, Shoulders, Triceps", 135.0, 4, 10 ) );
        w1.addExercise( new Exercise( "Squat", "Quads, Hamstrings, Calves", 135.0, 2, 10 ) );
        w1.addExercise( new Exercise( "Deadlift", "Back, Core, Legs", 135.0, 1, 10 ) );

        workoutService.save( w1 );

        printWorkouts();

    }

    private void printWorkouts () {
        for ( final DomainObject w : workoutService.findAll() ) {
            System.out.println( w );
        }
    }
}
