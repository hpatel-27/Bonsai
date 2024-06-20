package hpatel.Bonsai.models;

import java.io.Serializable;

/**
 * Root class for all of the persistent entities. Defines no fields or methods,
 * but is used to provide a common superclass that the `Service` methods can
 * use.
 *
 * @author Harsh Patel
 *
 */
abstract public class DomainObject {

    /**
     * Returns the ID of the object. The ID is used to uniquely identify this
     * object for persistent storage in the database.
     *
     * @return ID of the object
     */
    public abstract Serializable getId ();
}
