package hpatel.Bonsai.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Users are anyone interacting with the front end of Bonsai. The User class
 * defines their credentials for logging in, as well as their role, which
 * defines which tasks they are allowed to perform.
 *
 * @author Harsh Patel
 *
 */
@SuppressWarnings ( "serial" )
@Entity
public class User extends DomainObject implements UserDetails {

    /** User id */
    @Id
    @GeneratedValue
    private Long   id;

    /** User name */
    @Column ( nullable = false, unique = true )
    private String username;

    /** User password */
    private String password;

    /** User role */
    private String role;

    /**
     * Empty default constructor
     */
    public User () {
        // Empty constructor
    }

    /**
     * Gets the user id
     *
     * @return id
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Sets the user id
     *
     * @param id
     *            The id to set
     *
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Gets the username
     *
     * @return username
     */
    @Override
    public String getUsername () {
        return username;
    }

    /**
     * Sets the username
     *
     * @param username
     *            The username to set
     */
    public void setUsername ( final String username ) {
        this.username = username;
    }

    /**
     * Gets the user password
     *
     * @return password
     */
    @Override
    public String getPassword () {
        return password;
    }

    /**
     * Gets the user role
     *
     * @return role
     */
    public String getRole () {
        return role;
    }

    /**
     * Sets the user role
     *
     * @param role
     *            role to set
     */
    public void setRole ( final String role ) {
        this.role = role;
    }

    /**
     * Sets the user password
     *
     * @param password
     *            password to set
     */
    public void setPassword ( final String password ) {
        this.password = password;
    }

    @Override
    public Collection< ? extends GrantedAuthority> getAuthorities () {
        final Set<GrantedAuthority> roles = new HashSet<>();
        roles.add( new SimpleGrantedAuthority( "ROLE_" + role ) );
        return roles;
    }

    /**
     * Returns whether the account is not expired
     *
     * @returns true
     */
    @Override
    public boolean isAccountNonExpired () {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * Returns whether the account is not locked
     *
     * @returns true
     */
    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    /**
     * Returns whether the credentials are not expired
     *
     * @returns true
     */
    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    /**
     * Returns whether the account is enabled
     *
     * @returns true
     */
    @Override
    public boolean isEnabled () {
        return true;
    }
}
