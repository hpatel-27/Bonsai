package hpatel.Bonsai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hpatel.Bonsai.models.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    /**
     * Finds an Exercise object with the provided name. Spring will generate
     * code to make this happen.
     *
     * @param name
     *            Name of the exercise
     * @return Found exercise, null if none.
     */
    Exercise findByName ( String name );
}
