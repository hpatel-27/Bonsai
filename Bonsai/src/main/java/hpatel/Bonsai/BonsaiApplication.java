package hpatel.Bonsai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entrypoint to the Bonsai Fitness Application. Allows running as Java
 * application.
 *
 * @author Harsh Patel
 *
 */
@SpringBootApplication ( scanBasePackages = { "hpatel.Bonsai" } )
public class BonsaiApplication {

    /**
     * Main method
     *
     * @param args
     *            Command-line arguments
     *
     */
    public static void main ( final String[] args ) {
        SpringApplication.run( BonsaiApplication.class, args );
    }
}
