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
    private long      id;

    /** Weigh in date */
    private LocalDate date;

    /** Weigh in weight */
    private double    weight;

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
    public void setDate ( final LocalDate date ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "Must provide a date for the weigh in." );
        }
        this.date = date;
    }

    /**
     * Returns date of the weigh in.
     *
     * @return Returns the date.
     */
    public LocalDate getDate () {
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