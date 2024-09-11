package hpatel.Bonsai.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.services.ExerciseService;

/**
 * Class to test the exercise class
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class ExerciseTest {

    /** Exercise service object */
    @Autowired
    private ExerciseService exerciseService;

    /** Delete the existing exercises before each test */
    @BeforeEach
    public void setup () {
        exerciseService.deleteAll();
    }

    @Test
    @Transactional
    public void testValidExercise () {

        // create an object, save the object, and check that it matches
        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 135.0, ex1.getWeight() );
        assertEquals( 4, ex1.getSets() );
        assertEquals( 10, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        Exercise e1 = exercises.get( 0 );

        assertEquals( "Bench Press", e1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e1.getDescription() );
        assertEquals( 135.0, e1.getWeight() );
        assertEquals( 4, e1.getSets() );
        assertEquals( 10, e1.getReps() );

        // create a new object, save it, and check that both the objects now in
        // the database match the original objects
        final Exercise ex2 = new Exercise( "Squat", "Quads, Hamstrings, Calves, Core", 245.0, 4, 6 );

        assertEquals( "Squat", ex2.getName() );
        assertEquals( "Quads, Hamstrings, Calves, Core", ex2.getDescription() );
        assertEquals( 245.0, ex2.getWeight() );
        assertEquals( 4, ex2.getSets() );
        assertEquals( 6, ex2.getReps() );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        exerciseService.save( ex2 );

        exercises = exerciseService.findAll();

        assertEquals( 2, exercises.size() );

        e1 = exercises.get( 0 );

        assertEquals( "Bench Press", e1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e1.getDescription() );
        assertEquals( 135.0, e1.getWeight() );
        assertEquals( 4, e1.getSets() );
        assertEquals( 10, e1.getReps() );

        Exercise e2 = exercises.get( 1 );

        assertEquals( "Squat", e2.getName() );
        assertEquals( "Quads, Hamstrings, Calves, Core", e2.getDescription() );
        assertEquals( 245.0, e2.getWeight() );
        assertEquals( 4, e2.getSets() );
        assertEquals( 6, e2.getReps() );

        // delete the first object

        exerciseService.delete( e1 );

        // check what is left in the database
        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        e2 = exercises.get( 0 );

        assertEquals( "Squat", e2.getName() );
        assertEquals( "Quads, Hamstrings, Calves, Core", e2.getDescription() );
        assertEquals( 245.0, e2.getWeight() );
        assertEquals( 4, e2.getSets() );
        assertEquals( 6, e2.getReps() );

        exerciseService.save( e1 );
        exercises = exerciseService.findAll();

        assertEquals( 2, exercises.size() );

        exerciseService.deleteAll();
        exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( e2 );

        final Exercise foundByName = exerciseService.findByName( "Squat" );

        assertEquals( "Squat", foundByName.getName() );
        assertEquals( "Quads, Hamstrings, Calves, Core", foundByName.getDescription() );
        assertEquals( 245.0, foundByName.getWeight() );
        assertEquals( 4, foundByName.getSets() );
        assertEquals( 6, foundByName.getReps() );
    }

    @Test
    @Transactional
    public void testInvalidName () {

        final Exception e1 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( null, "Chest, Triceps, Shoulders", 135.0, 4, 10 ) );

        assertEquals( "No exercise name was provided.", e1.getMessage() );

        final Exception e2 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "", "Chest, Triceps, Shoulders", 135.0, 4, 10 ) );

        assertEquals( "No exercise name was provided.", e2.getMessage() );

        // test trying to save after having these invalid ones

        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 135.0, ex1.getWeight() );
        assertEquals( 4, ex1.getSets() );
        assertEquals( 10, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        final Exercise e = exercises.get( 0 );

        assertEquals( "Bench Press", e.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e.getDescription() );
        assertEquals( 135.0, e.getWeight() );
        assertEquals( 4, e.getSets() );
        assertEquals( 10, e.getReps() );
    }

    @Test
    @Transactional
    public void testInvalidDescription () {

        final Exception e1 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", null, 135.0, 4, 10 ) );

        assertEquals( "No exercise description was provided.", e1.getMessage() );

        final Exception e2 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "", 135.0, 4, 10 ) );

        assertEquals( "No exercise description was provided.", e2.getMessage() );

        // test trying to save after having these invalid ones

        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 135.0, ex1.getWeight() );
        assertEquals( 4, ex1.getSets() );
        assertEquals( 10, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        final Exercise e = exercises.get( 0 );

        assertEquals( "Bench Press", e.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e.getDescription() );
        assertEquals( 135.0, e.getWeight() );
        assertEquals( 4, e.getSets() );
        assertEquals( 10, e.getReps() );
    }

    @Test
    @Transactional
    public void testInvalidWeight () {

        // test the boundary
        final Exception e1 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", -1.0, 4, 10 ) );

        assertEquals( "Weight cannot be negative.", e1.getMessage() );

        // test an extreme invalid value
        final Exception e2 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", -100000.0, 4, 10 ) );

        assertEquals( "Weight cannot be negative.", e2.getMessage() );

        // test the valid side of the boundary
        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 0, 4, 10 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 0, ex1.getWeight() );
        assertEquals( 4, ex1.getSets() );
        assertEquals( 10, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        final Exercise e = exercises.get( 0 );

        assertEquals( "Bench Press", e.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e.getDescription() );
        assertEquals( 0, e.getWeight() );
        assertEquals( 4, e.getSets() );
        assertEquals( 10, e.getReps() );
    }

    @Test
    @Transactional
    public void testInvalidSets () {
        // test the boundary
        final Exception e1 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 0, 10 ) );

        assertEquals( "At least one set must have been completed.", e1.getMessage() );

        // test an extreme invalid value
        final Exception e2 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, -1000000, 10 ) );

        assertEquals( "At least one set must have been completed.", e2.getMessage() );

        // test the valid side of the boundary
        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 1, 10 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 135.0, ex1.getWeight() );
        assertEquals( 1, ex1.getSets() );
        assertEquals( 10, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        final Exercise e = exercises.get( 0 );

        assertEquals( "Bench Press", e.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e.getDescription() );
        assertEquals( 135.0, e.getWeight() );
        assertEquals( 1, e.getSets() );
        assertEquals( 10, e.getReps() );
    }

    @Test
    @Transactional
    public void testInvalidReps () {
        // test the boundary
        final Exception e1 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 0 ) );

        assertEquals( "At least one rep must have been completed.", e1.getMessage() );

        // test an extreme invalid value
        final Exception e2 = assertThrows( IllegalArgumentException.class,
                () -> new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, -1000000 ) );

        assertEquals( "At least one rep must have been completed.", e2.getMessage() );

        // test the valid side of the boundary
        final Exercise ex1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 1, 1 );

        assertEquals( "Bench Press", ex1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", ex1.getDescription() );
        assertEquals( 135.0, ex1.getWeight() );
        assertEquals( 1, ex1.getSets() );
        assertEquals( 1, ex1.getReps() );

        List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 0, exercises.size() );

        exerciseService.save( ex1 );

        exercises = exerciseService.findAll();

        assertEquals( 1, exercises.size() );

        final Exercise e = exercises.get( 0 );

        assertEquals( "Bench Press", e.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e.getDescription() );
        assertEquals( 135.0, e.getWeight() );
        assertEquals( 1, e.getSets() );
        assertEquals( 1, e.getReps() );
    }

    @Test
    @Transactional
    public void testEquals () {
        final Exercise e1 = new Exercise( "Bench Press", "Chest, Core, Shoulders, Triceps", 135.0, 5, 5 );
        final Exercise e2 = new Exercise( "Bench Press", "Chest, Core, Shoulders, Triceps", 135.0, 5, 5 );
        final Exercise e3 = new Exercise( "Bench Press", "Chest, Core, Shoulders, Triceps", 225.0, 1, 1 );
        final Exercise e4 = new Exercise( "Squat", "Quads, Hamstrings, Core, Calves", 135.0, 5, 5 );

        assertTrue( e1.equals( e2 ) );
        assertTrue( e1.equals( e3 ) );
        assertFalse( e1.equals( e4 ) );
        assertFalse( e1.equals( null ) );
        assertFalse( e1.equals( new Object() ) );
    }
}
