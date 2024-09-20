package hpatel.Bonsai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/***
 * Configures login security
 * 
 * @author Harsh Patel
 *
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
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configures authorization for requests
     *
     * @param http
     *            Authorizes requests
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors() // Enable CORS
            .and()
            .csrf().disable() // Disable CSRF for simplicity
            .authorizeRequests()
            .antMatchers("/login", "/api/v1/users").permitAll() // Public access to login and user registration
            .antMatchers("/staff/*").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers("/admin/*").hasAuthority("ROLE_ADMIN")
            .antMatchers("/place-order").hasAnyAuthority("ROLE_USER", "ROLE_GUEST")
            .anyRequest().authenticated() // All other requests require authentication
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/home", true) // Redirect after successful login
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redirect to login on logout
                .permitAll()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    /**
     * Configures CORS to allow requests from the React frontend.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Update with your React app's URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Allow credentials (cookies, etc.)
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Password encoder used
     *
     * @return Password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
