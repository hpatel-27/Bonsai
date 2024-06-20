package hpatel.Bonsai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.repositories.ExerciseRepository;

/**
 * Exercise service class
 *
 * @author Harsh Patel
 *
 */
public class ExerciseService extends Service<Exercise, Long> {

    /**
     * The instance of the repository.
     */
    @Autowired
    private ExerciseRepository repository;

    /**
     * Returns the repository for the ingredients.
     *
     * @return the repository
     */
    @Override
    protected JpaRepository<Exercise, Long> getRepository () {
        return repository;
    }

    /**
     * Finds an exercise by its name
     *
     * @param name
     *            The name of the exercise to find
     * @return Found exercise, null if none
     */
    public Exercise findByName ( final String name ) {
        return repository.findByName( name );
    }
}
