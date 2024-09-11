package hpatel.Bonsai.api;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import hpatel.Bonsai.common.TestUtils;
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.models.User;
import hpatel.Bonsai.models.Workout;

/**
 * Class for testing the API interface
 */
@ExtendWith ( SpringExtension.class )
@SpringBootTest
@AutoConfigureMockMvc
public class APITest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    /**
     * The web application context object
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }

    /**
     * Tests the API
     *
     * @throws Exception
     *             if the API call fails
     *
     * @throws UnsupportedEncodingException
     *             if the encoding fails
     *
     */
    @Test
    @Transactional
    public void testAPI () throws Exception {
        // get any workouts
        String workout = mvc.perform( get( "/api/v1/workouts" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        if ( !workout.contains( "Push" ) ) {
            // create the workout if its not there
            final Workout w = new Workout( "Push" );
            w.addExercise( new Exercise( "Skull Crushers", "Triceps", 70.0, 3, 15 ) );
            w.addExercise( new Exercise( "Tricep Extensions", "Triceps", 22.5, 3, 10 ) );

            // Add the new recipe
            mvc.perform( post( "/api/v1/workouts" ).contentType( MediaType.APPLICATION_JSON )
                    .content( TestUtils.asJsonString( w ) ) ).andExpect( status().isOk() );

        }

        // get the workouts again
        workout = mvc.perform( get( "/api/v1/workouts" ) ).andDo( print() ).andExpect( status().isOk() ).andReturn()
                .getResponse().getContentAsString();

        // check that the push workout was retrieved
        assertTrue( workout.contains( "Push" ) );

        // create a new user
        final User u1 = new User();
        u1.setUsername( "user1" );
        u1.setPassword( "pw" );
        u1.setRole( "customer" );
        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( u1 ) ) ).andExpect( status().isOk() );

        final String user = mvc.perform( get( "/api/v1/users/user1" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        assertTrue( user.contains( "user1" ) );
    }

}
