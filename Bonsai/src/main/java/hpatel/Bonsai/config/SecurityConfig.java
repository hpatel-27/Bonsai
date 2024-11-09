package hpatel.Bonsai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures login security
 *
 * @author Harsh Patel
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Service to retrieve UserDetails from the repository
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Configures authentication
     *
     * @param auth
     *            Builds authentication
     */
    @Override
    protected void configure ( final AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userDetailsService ).passwordEncoder( passwordEncoder() );
    }

    /**
     * Configures authorization for requests
     *
     * @param http
     *            Authorizes requests
     */
    @Override
    public void configure ( final HttpSecurity http ) throws Exception {
        http.authorizeRequests().antMatchers( "/login" ).permitAll().antMatchers( "/api/v1/users" ).permitAll()
                .antMatchers( "/staff/*" ).hasAnyAuthority( "ROLE_ADMIN", "ROLE_BARISTA" ).antMatchers( "/admin/*" )
                .hasAuthority( "ROLE_ADMIN" ).antMatchers( "/place-order" )
                .hasAnyAuthority( "ROLE_CUSTOMER", "ROLE_GUEST" ).anyRequest().authenticated().and().csrf().disable()
                .formLogin().loginPage( "/login" );
    }

    /**
     * Password encoder used
     *
     * @return Password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
