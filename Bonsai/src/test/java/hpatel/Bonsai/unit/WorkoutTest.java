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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

/**
 * Class to test the workout class
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
@ActiveProfiles ( "test" )
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

        List<Workout> workouts = workoutService.findAll();

        assertEquals( 0, workouts.size() );

        workoutService.save( w1 );

        workouts = workoutService.findAll();

        assertEquals( 1, workouts.size() );

        Workout ws1 = workouts.get( 0 );

        assertEquals( "Push", ws1.getName() );
        assertEquals( LocalDate.now().toString(), ws1.getDate() );
        assertEquals( 0, ws1.getExercises().size() );

        // now add two exercises to the workout and then save it and check that
        // the object matches
        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );
        final Exercise ex2 = new Exercise( "Squat", "Quads, Hamstrings, Calves, Core", 245.0, 4, 6 );

        ws1.addExercise( ex1 );
        ws1.addExercise( ex2 );

        assertEquals( 2, ws1.getExercises().size() );

        workoutService.save( ws1 );

        workouts = workoutService.findAll();

        assertEquals( 1, workouts.size() );

        ws1 = workouts.get( 0 );

        assertEquals( "Push", ws1.getName() );
        assertEquals( LocalDate.now().toString(), ws1.getDate() );
        assertEquals( 2, ws1.getExercises().size() );

        List<Exercise> exercises = ws1.getExercises();

        final Exercise e1 = exercises.get( 0 );
        final Exercise e2 = exercises.get( 1 );

        assertEquals( "Bench Press", e1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e1.getDescription() );
        assertEquals( 135.0, e1.getWeight() );
        assertEquals( 4, e1.getSets() );
        assertEquals( 10, e1.getReps() );

        assertEquals( "Squat", e2.getName() );
        assertEquals( "Quads, Hamstrings, Calves, Core", e2.getDescription() );
        assertEquals( 245.0, e2.getWeight() );
        assertEquals( 4, e2.getSets() );
        assertEquals( 6, e2.getReps() );

        // create an object, save the object, and check that it matches
        final Workout w2 = new Workout( "Pull" );

        assertEquals( "Pull", w2.getName() );
        assertEquals( LocalDate.now().toString(), w2.getDate() );
        assertEquals( 0, w2.getExercises().size() );

        workouts = workoutService.findAll();

        assertEquals( 1, workouts.size() );

        workoutService.save( w2 );

        workouts = workoutService.findAll();

        assertEquals( 2, workouts.size() );

        Workout ws2 = workouts.get( 1 );

        assertEquals( "Pull", ws2.getName() );
        assertEquals( LocalDate.now().toString(), ws2.getDate() );
        assertEquals( 0, ws2.getExercises().size() );

        final Exercise ex3 = new Exercise( "Deadlift", "Core", 315.0, 1, 5 );
        final Exercise ex4 = new Exercise( "Row", "Back, Biceps, Forearms", 185.0, 2, 10 );

        ws2.addExercise( ex3 );
        ws2.addExercise( ex4 );

        workoutService.save( ws2 );

        workouts = workoutService.findAll();

        assertEquals( 2, workouts.size() );

        ws2 = workouts.get( 1 );

        assertEquals( "Pull", ws2.getName() );
        assertEquals( LocalDate.now().toString(), ws2.getDate() );
        assertEquals( 2, ws2.getExercises().size() );

        exercises = ws2.getExercises();

        final Exercise e3 = exercises.get( 0 );
        final Exercise e4 = exercises.get( 1 );

        assertEquals( "Deadlift", e3.getName() );
        assertEquals( "Core", e3.getDescription() );
        assertEquals( 315.0, e3.getWeight() );
        assertEquals( 1, e3.getSets() );
        assertEquals( 5, e3.getReps() );

        assertEquals( "Row", e4.getName() );
        assertEquals( "Back, Biceps, Forearms", e4.getDescription() );
        assertEquals( 185.0, e4.getWeight() );
        assertEquals( 2, e4.getSets() );
        assertEquals( 10, e4.getReps() );

    }
}
