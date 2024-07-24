package hpatel.Bonsai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hpatel.Bonsai.models.Workout;

/**
 * WorkoutRepository is used to provide CRUD operations for the Workout model.
 * Spring will generate appropriate code with JPA.
 *
 * @author Harsh Patel
 *
 */
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Finds a Workout object with the provided name. Spring will generate code
     * to make this happen.
     *
     * @param name
     *            Name of the workout
     * @return Found workout, null if none.
     */
    Workout findByName ( String name );

    /**
     * Finds a Workout object with the provided name. Spring will generate code
     * to make this happen.
     *
     * @param name
     *            Name of the workout
     * @return Found workout, null if none.
     */
    Workout findByDate ( String date );

}
