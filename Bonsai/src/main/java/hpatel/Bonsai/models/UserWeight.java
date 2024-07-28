package hpatel.Bonsai.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The user's weight for a particular date.
 *
 * @author Harsh Patel
 */
@Entity
public class UserWeight extends DomainObject {

    /** User weight id */
    @Id
    @GeneratedValue
    private long   id;

    /** Weigh in date */
    private String date;

    /** Weigh in weight */
    private double weight;

    /**
     * Constructor for when only one of the fields is provided
     *
     * @param weight
     */
    public UserWeight () {
        // empty constructor
    }

    /**
     * Constructor for both private fields of the object
     *
     * @param date
     *            The date to set for the object
     * @param weight
     *            The weight to set for the object
     */
    public UserWeight ( final String date, final double weight ) {
        setDate( date );
        setWeight( weight );
    }

    /**
     * Constructor for when only one of the fields is provided
     *
     * @param weight
     */
    public UserWeight ( final double weight ) {
        setDate( LocalDate.now().toString() );
        setWeight( weight );
    }

    @Override
    public Serializable getId () {
        return id;
    }

    /**
     * Set the ID of the UserWeight object (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Sets the date of the weigh in.
     *
     * @param date
     *            The date to set.
     *
     * @throws IllegalArgumentException
     *             when no date is provided
     */
    public void setDate ( final String date ) {
        if ( date == null || date.length() != 10 ) {
            throw new IllegalArgumentException( "Must provide a date for the weigh in." );
        }
        this.date = date;
    }

    /**
     * Returns date of the weigh in.
     *
     * @return Returns the date.
     */
    public String getDate () {
        return date;
    }

    /**
     * Sets the weight of the user for this weigh in
     *
     * @param weight
     *            The weight to set
     *
     * @throws IllegalArgumentException
     *             When a valid weight is not provided
     */
    public void setWeight ( final double weight ) {
        if ( weight < 0 ) {
            throw new IllegalArgumentException( "Must provide a non-negative weight." );
        }

        this.weight = weight;
    }

    /**
     * Gets the user's weight for this particular weigh in
     *
     * @return weight The user's weight
     */
    public double getWeight () {
        return weight;
    }

}
