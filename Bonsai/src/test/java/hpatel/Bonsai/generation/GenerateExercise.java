package hpatel.Bonsai.generation;

import org.junit.Assert;
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

//    /**
//     * The ingredient service
//     */
//    @Autowired
//    private ExerciseService exerciseService;
//
//    /**
//     * Test the creation of ingredients
//     */
//    @Test
//    @Transactional
//    public void testCreateExercises () {
//        exerciseService.deleteAll();
//
//        final Exercise e1 = new Exercise();
//
//        exerciseService.save( e1 );
//
//        final Exercise e2 = new Exercise();
//
//        exerciseService.save( e2 );
//
//        Assert.assertEquals( 2, exerciseService.count() );
//
//    }
}
