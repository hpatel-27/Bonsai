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
    public Exercise ( final String name, final String description ) {
        setName( name );
        setDescription( description );
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
        return "Exercise [id=" + id + ", name=" + name + ", description=" + description + "]";
    }
}
