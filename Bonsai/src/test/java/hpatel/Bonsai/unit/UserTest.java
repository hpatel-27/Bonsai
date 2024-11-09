package hpatel.Bonsai.unit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import hpatel.Bonsai.TestConfig;
import hpatel.Bonsai.models.User;
import hpatel.Bonsai.services.UserService;

/**
 * Class to test the user methods
 */
@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
@ActiveProfiles ( "test" )
public class UserTest {
    /**
     * The user service object
     */
    @Autowired
    private UserService service;

    /**
     * Deletes all users before each test
     */
    @BeforeEach
    public void setup () {
        service.deleteAll();
    }

    /**
     * Tests the add user functionality
     */
    @Test
    @Transactional
    public void testAddUser () {
        final User u1 = new User();
        u1.setUsername( "user1" );
        u1.setPassword( "pw" );
        u1.setRole( "admin" );
        service.save( u1 );

        final User u2 = new User();
        u2.setUsername( "user2" );
        u2.setPassword( "pw" );
        u2.setRole( "admin" );
        service.save( u2 );

        final List<User> recipes = service.findAll();
        Assertions.assertEquals( 2, recipes.size(), "Creating two users should result in two users in the database" );

        Assertions.assertEquals( u1, recipes.get( 0 ), "The retrieved user should match the created one" );
        Assertions.assertEquals( u1.getUsername(), recipes.get( 0 ).getUsername(),
                "The retrieved user should match the created one" );
        Assertions.assertEquals( u1.getRole(), recipes.get( 0 ).getRole(),
                "The retrieved user should match the created one" );
        Assertions.assertEquals( u2, recipes.get( 1 ), "The retrieved user should match the created one" );
        Assertions.assertEquals( u2.getUsername(), recipes.get( 1 ).getUsername(),
                "The retrieved user should match the created one" );
        Assertions.assertEquals( u2.getRole(), recipes.get( 1 ).getRole(),
                "The retrieved user should match the created one" );
    }

    /**
     * Tests the delete user functionality
     */
    @Test
    @Transactional
    public void testDeleteUser () {
        Assertions.assertEquals( 0, service.findAll().size(), "There should be no users" );

        final User u1 = new User();
        u1.setUsername( "user1" );
        u1.setPassword( "pw" );
        u1.setRole( "admin" );
        service.save( u1 );
        final User u2 = new User();
        u2.setUsername( "user2" );
        u2.setPassword( "pw" );
        u2.setRole( "admin" );
        service.save( u2 );
        final User u3 = new User();
        u3.setUsername( "user3" );
        u3.setPassword( "pw" );
        u3.setRole( "admin" );
        service.save( u3 );

        Assertions.assertEquals( 3, service.count(), "There should be three users in the database" );

        service.deleteAll();

        Assertions.assertEquals( 0, service.count(), "`service.deleteAll()` should remove everything" );
    }
}
