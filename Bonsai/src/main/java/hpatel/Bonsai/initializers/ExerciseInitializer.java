package hpatel.Bonsai.initializers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import hpatel.Bonsai.repositories.ExerciseRepository;

/**
 * Initializes exercises into the database
 *
 * @author Harsh Patel
 *
 */
@Configuration
public class ExerciseInitializer {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostConstruct
    public void intializeExercises () {
        // // check if there are no existing workouts
        // if ( exerciseRepository.count() == 0 ) {
        // final Exercise e1 = new Exercise();
        //
        // }
    }
}
