package hpatel.Bonsai.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Exercise object for each workout. Create a few commonly performed ones at
 * startup and store in database.
 *
 * @author Harsh Patel
 */
@Entity
public class Exercise extends DomainObject {

    /** Id for the exercise in the database */
    @Id
    @GeneratedValue
    private long   id;

    /** Name of exercise */
    private String name;

    /** Description of the exercise */
    private String description;

    /** Weight used for the exercise */
    private double weight;

    /** Number of sets done */
    private int    sets;

    /** Number of reps done */
    private int    reps;

    /**
     * Empty default constructor
     */
    public Exercise () {
        // Empty
    }

    /**
     * Constructs a new exercise using an exercise name and a description
     *
     * @param exercise
     *            Exercise to set
     * @param description
     *            Description to set
     */
    public Exercise ( final String name, final String description, final double weight, final int sets,
            final int reps ) {
        setName( name );
        setDescription( description );
        setWeight( weight );
        setSets( sets );
        setReps( reps );
    }

    /**
     * Returns the id for the exercise
     *
     * @return the id
     */
    @Override
    public Serializable getId () {
        return id;
    }

    /**
     * Sets the id for the exercise
     *
     *
     * @param id
     *            The id to set
     */
    public void setId ( final long id ) {
        this.id = id;
    }

    /**
     * Returns the name of the exercise
     *
     * @return the exercise
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the name of the exercise
     *
     * @param name
     *            The name to set
     * @throws IllegalArgumentException
     *             When an invalid name is given
     */
    public void setName ( final String name ) {
        if ( name == null || name.length() < 1 ) {
            throw new IllegalArgumentException( "No exercise name was provided." );
        }
        this.name = name;
    }

    /**
     * Returns the description of the exercise
     *
     * @return the description
     */
    public String getDescription () {
        return description;
    }

    /**
     * Sets the description of the exercise
     *
     * @param description
     *            The description to set
     * @throws IllegalArgumentException
     *             When an invalid description is given
     */
    public void setDescription ( final String description ) {
        if ( description == null || description.length() < 1 ) {
            throw new IllegalArgumentException( "No exercise description was provided." );
        }
        this.description = description;
    }

    /**
     * Returns the Weight used for the exercise
     *
     * @return the weight
     */
    public double getWeight () {
        return weight;
    }

    /**
     * Sets the weight for the exercise
     *
     * @param weight
     *            The weight to set
     *
     * @throws IllegalArgumentException
     *             If weight is negative
     */
    public void setWeight ( final double weight ) {
        if ( weight < 0 ) {
            throw new IllegalArgumentException( "Weight cannot be negative." );
        }
        this.weight = weight;
    }

    /**
     * Returns the sets completed for the exercise
     *
     * @return the sets
     */
    public int getSets () {
        return sets;
    }

    /**
     * Sets the number of sets completed for the exercise
     *
     * @param sets
     *            The sets to set
     *
     * @throws IllegalArgumentException
     *             If sets is negative
     */
    public void setSets ( final int sets ) {
        if ( sets < 1 ) {
            throw new IllegalArgumentException( "At least one set must have been completed." );
        }
        this.sets = sets;
    }

    /**
     * Returns the reps completed used for the exercise
     *
     * @return the reps
     */
    public int getReps () {
        return reps;
    }

    /**
     * Sets the number of reps completed for the exercise
     *
     * @param reps
     *            The reps to set
     *
     * @throws IllegalArgumentException
     *             If reps is negative
     */
    public void setReps ( final int reps ) {
        if ( reps < 1 ) {
            throw new IllegalArgumentException( "At least one rep must have been completed." );
        }
        this.reps = reps;
    }

    /**
     * Two exercises are equal if they have the same value for their name
     *
     * @param obj
     *            Object to compare
     * @return True if the passed object is equal to this
     */
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
        final Exercise other = (Exercise) obj;
        return name.equals( other.name );
    }

    /**
     * Generates the hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode () {
        return super.hashCode();
    }

    /**
     * Generates a string for the Exercise
     */
    @Override
    public String toString () {
        return "Exercise [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
                + ", sets=" + sets + ", reps=" + reps + "]";
    }
}
