package hpatel.Bonsai;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ComponentScan ( { "hpatel.Bonsai" } )
@ActiveProfiles ( "test" )
public class TestConfig {
}
