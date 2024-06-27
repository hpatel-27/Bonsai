package hpatel.Bonsai.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Workout for the Bonsai fitness application. Exercise is tied to the database
 * using Hibernate libraries. See WorkoutRepository and WorkoutService for the
 * other two pieces used for database support.
 *
 * @author Harsh Patel
 *
 */
@Entity
public class Workout extends DomainObject {

    /** Workout id */
    @Id
    @GeneratedValue
    private Long                 id;

    /** Workout name */
    private String               name;

    /** Workout date */
    private LocalDate            date;

    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private final List<Exercise> exercises;

    public Workout () {
        this.name = "";
        this.date = LocalDate.now();
        exercises = new ArrayList<Exercise>();
    }

    @Override
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Workout (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Returns name of the workout.
     *
     * @return Returns the name.
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the workout name.
     *
     * @param name
     *            The name to set.
     */
    public void setName ( final String name ) {
        // Lets allow them to not have a name for the workout?
        this.name = name;
    }

    /**
     * Returns date of the workout.
     *
     * @return Returns the date.
     */
    public LocalDate getDate () {
        return date;
    }

    /**
     * Sets the workout date.
     *
     * @param date
     *            The date to set.
     */
    public void setDate ( final LocalDate date ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "Must provide a date for the workout." );
        }
        this.date = date;
    }

    /**
     * Check if there are no exercises
     *
     * @return true if exercise list is empty
     */
    public boolean checkExercise () {
        return exercises.size() == 0;
    }

    /**
     * Returns the list of exercises
     *
     * @return the list of exercises
     */
    public List<Exercise> getExercises () {
        return exercises;
    }

    public Exercise getExercise ( final Exercise exercise ) {
        // check if the exercise actually exists first
        if ( !exercises.contains( exercise ) ) {
            return null;
        }
        // since it exists we don't have to worry about checking for
        // error state
        return exercises.get( exercises.indexOf( exercise ) );
    }

    public void addExercise ( final Exercise exercise ) {
        if ( exercise.getName().isEmpty() || exercise.getWeight() < 0 || exercise.getReps() < 0
                || exercise.getSets() < 0 ) {
            throw new IllegalArgumentException( "Exercise does not provide all the necessary information." );
        }

        // if the exercise is already in there, just send that to the user
        // let them edit or remove the already existing exercise that matches
        if ( exercises.contains( exercise ) ) {
            // get the old exercise and update the values of its fields to match
            // the new one
            final Exercise e = exercises.get( exercises.indexOf( exercise ) );
            e.setDescription( exercise.getDescription() );
            e.setName( exercise.getName() );
            e.setWeight( exercise.getWeight() );
            e.setReps( exercise.getReps() );
            e.setSets( exercise.getSets() );
        }
        else {
            exercises.add( exercise );
        }
    }

    /**
     * Removes the exercise matching the one passed
     *
     * @param exercise
     *            Exercise to remove
     * @return True if the exercise could be removed, false if it wasn't there
     */
    public boolean removeExercise ( final Exercise exercise ) {
        return exercises.remove( exercise );
    }

    public void updateWorkout ( final Workout workout ) {
        // set the name and date
        this.setName( workout.getName() );
        this.setDate( workout.getDate() );

        // get the new list of exercises
        final List<Exercise> newExercises = workout.getExercises();

        // delete any exercises which didn't make it over to the updated workout
        for ( int i = 0; i < this.exercises.size(); ) {
            if ( !newExercises.contains( exercises.get( i ) ) ) {
                exercises.remove( i );
            }
            else {
                i++;
            }
        }

        // now that all the unneeded exercises are gone, use the addExercise
        // method to update or add all the exercises
        for ( final Exercise e : workout.getExercises() ) {
            this.addExercise( e );
        }
    }

    /**
     * Returns the name of the recipe.
     *
     * @return String
     */
    @Override
    public String toString () {
        return ( name + "\n" + exercises.toString() );
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        Integer result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }

    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Workout other = (Workout) obj;
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        }
        else if ( !name.equals( other.name ) ) {
            return false;
        }
        return true;
    }
}
