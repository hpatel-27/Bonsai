package hpatel.Bonsai.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.services.ExerciseService;

/**
 * Class to test the creation of ingredients
 */
@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class GenerateExercise {

    /**
     * The exercise service
     */
    @Autowired
    private ExerciseService exerciseService;

    /**
     * Test the creation of exercises
     */
    @Test
    @Transactional
    public void testCreateExercises () {
        exerciseService.deleteAll();

        final Exercise e1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 10 );

        exerciseService.save( e1 );

        final Exercise e2 = new Exercise( "Deadlift", "Back, Core, Legs", 315.0, 3, 5 );

        exerciseService.save( e2 );

        assertEquals( 2, exerciseService.count() );

        final List<Exercise> exercises = exerciseService.findAll();

        assertEquals( 2, exercises.size() );

        assertEquals( "Bench Press", e1.getName() );
        assertEquals( "Chest, Triceps, Shoulders", e1.getDescription() );
        assertEquals( 135.0, e1.getWeight() );
        assertEquals( 4, e1.getSets() );
        assertEquals( 10, e1.getReps() );

        assertEquals( "Deadlift", e2.getName() );
        assertEquals( "Back, Core, Legs", e2.getDescription() );
        assertEquals( 315.0, e2.getWeight() );
        assertEquals( 3, e2.getSets() );
        assertEquals( 5, e2.getReps() );

    }
}
