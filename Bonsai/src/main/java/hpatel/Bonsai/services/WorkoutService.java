package hpatel.Bonsai.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.repositories.WorkoutRepository;

/**
 * The WorkoutService is used to handle CRUD operations on the Workout model. In
 * addition to all functionality from `Service`, we also have functionality for
 * retrieving a single Workout by name.
 *
 * @author Harsh Patel
 *
 */
@Component
@Transactional
public class WorkoutService extends Service<Workout, Long> {

    /**
     * WorkoutRepository, to be autowired in by Spring and provide CRUD
     * operations on Workout model.
     */
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    protected JpaRepository<Workout, Long> getRepository () {
        return workoutRepository;
    }

    /**
     * Find a workout with the provided name
     *
     * @param name
     *            Name of the workout to find
     * @return found workout, null if none
     */
    public Workout findByName ( final String name ) {
        return workoutRepository.findByName( name );
    }

    /**
     * Find a workout with the provided name
     *
     * @param name
     *            Name of the workout to find
     * @return found workout, null if none
     */
    public Workout findByDate ( final String date ) {
        return workoutRepository.findByDate( date );
    }

}
