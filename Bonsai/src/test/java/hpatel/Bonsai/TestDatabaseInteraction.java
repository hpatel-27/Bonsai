package hpatel.Bonsai;

import static org.junit.Assert.assertNull;
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

import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

/**
 * Class to test the interaction with the database
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )

public class TestDatabaseInteraction {

    /**
     * The workout service for the database interaction
     */
    @Autowired
    private WorkoutService workoutService;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        workoutService.deleteAll();
    }

    @Test
    @Transactional
    public void testWorkouts () {
        // create a new workout object
        final Workout w = new Workout();

        // set the values for the workout
        w.setName( "Push" );
        // dont need to set date, since it is generated in the constructor

        final Exercise e1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );
        final Exercise e2 = new Exercise( "Squat", "Quads, Hamstrings, Calves", 245.0, 5, 5 );
        final Exercise e3 = new Exercise( "Deadlift", "Core, Back, Legs", 315.0, 3, 5 );
        w.addExercise( e1 );
        w.addExercise( e2 );
        w.addExercise( e3 );

        workoutService.save( w );

        // get the list of workouts from service
        final List<Workout> dbWorkouts = workoutService.findAll();

        assertEquals( 1, dbWorkouts.size() );

        Workout dbWorkout = dbWorkouts.get( 0 );

        // check the correctness of all the fields from the saved workout

        assertEquals( w.getName(), dbWorkout.getName() );
        assertEquals( w.getDate(), dbWorkout.getDate() );
        assertEquals( w.getExercise( e1 ), dbWorkout.getExercise( e1 ) );
        assertEquals( w.getExercise( e2 ), dbWorkout.getExercise( e2 ) );
        assertEquals( w.getExercise( e3 ), dbWorkout.getExercise( e3 ) );

        // test find by name
        assertEquals( w, workoutService.findByName( "Push" ) );

        final String testDate = LocalDate.now().toString();
        dbWorkout.setDate( testDate );

        final Exercise e4 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 225.0, 1, 1 );

        dbWorkout.addExercise( e4 );
        workoutService.save( dbWorkout );

        dbWorkout = workoutService.findByName( "Push" );
        assertEquals( testDate, dbWorkout.getDate() );

        assertEquals( "Bench Press", dbWorkout.getExercise( e4 ).getName() );
        assertEquals( "Chest, Triceps, Shoulders", dbWorkout.getExercise( e4 ).getDescription() );
        assertEquals( 225.0, dbWorkout.getExercise( e4 ).getWeight() );
        assertEquals( 1, dbWorkout.getExercise( e4 ).getSets() );
        assertEquals( 1, dbWorkout.getExercise( e4 ).getReps() );

        // test delete workout function
        workoutService.delete( dbWorkout );

        // ensure the delete
        assertNull( workoutService.findByName( "Push" ) );
        assertEquals( 0, (int) workoutService.count() );

        // make a new workout

        final Workout w2 = new Workout();
        final String testDate2 = LocalDate.now().toString();
        w2.setName( "Pull" );
        w2.setDate( testDate2 );

        w2.addExercise( new Exercise( "Push Ups", "Chest, Triceps, Shoulders", 135.0, 4, 10 ) );
        w2.addExercise( new Exercise( "Quad Extensions", "Quads", 170.0, 3, 10 ) );
        w2.addExercise( new Exercise( "Hamstring Curls", "Hamstrings", 90.0, 3, 10 ) );

        // save the two made recipes
        workoutService.save( w );
        workoutService.save( w2 );

        // check that the workouts were added
        assertEquals( 2, (int) workoutService.count() );
        assertEquals( w, workoutService.findByName( "Push" ) );
        assertEquals( w2, workoutService.findByName( "Pull" ) );

        workoutService.deleteAll();

        assertNull( workoutService.findByName( "Push" ) );
        assertNull( workoutService.findByName( "Pull" ) );
        assertEquals( 0, (int) workoutService.count() );

    }
}
