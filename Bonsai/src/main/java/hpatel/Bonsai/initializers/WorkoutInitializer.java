package hpatel.Bonsai.initializers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import hpatel.Bonsai.repositories.ExerciseRepository;
import hpatel.Bonsai.repositories.WorkoutRepository;

/**
 * Initializes workouts into the database
 *
 * @author Harsh Patel
 *
 */
@Configuration
public class WorkoutInitializer {

    @Autowired
    private WorkoutRepository  workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostConstruct
    public void intializeWorkouts () {
        // // check if there are no existing workouts
        // if ( workoutRepository.count() == 0 ) {
        //
        // // Make a new workout for Back and Biceps
        // final Workout w1 = new Workout( "Back, Biceps" );
        //
        // // Change the date for September 15, 2024
        // w1.setDate( "2024-09-15" );
        //
        // // Make some exercises and save them to the db
        // final Exercise e1 = new Exercise( "Deadlift", "Core, Back, Biceps,
        // Legs", 315, 3, 1 );
        // final Exercise e2 = new Exercise( "Bent-Over Row", "Back, Biceps",
        // 165, 3, 10 );
        // final Exercise e3 = new Exercise( "Standard Curls", "Biceps", 30, 3,
        // 10 );
        //
        // exerciseRepository.save( e1 );
        // exerciseRepository.save( e2 );
        // exerciseRepository.save( e3 );
        //
        // // add the exercises to the workout
        // w1.addExercise( e1 );
        // w1.addExercise( e2 );
        // w1.addExercise( e3 );
        //
        // // save the workout
        // workoutRepository.save( w1 );
        // System.out.println( "Initial data inserted." );
        // }
        // else {
        // System.out.println( "Data already exists, skipping initialization."
        // );
        // }
    }
}
