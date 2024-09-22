package hpatel.Bonsai.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import hpatel.Bonsai.common.TestUtils;
import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

/**
 * Class to test the workout api
 *
 * @author Harsh Patel
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith ( SpringExtension.class )
@ActiveProfiles ( "test" )
public class APIWorkoutTest {

    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WorkoutService        service;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();

        service.deleteAll();
    }

    /**
     * Tests that the workout is correctly posted to the repository.
     *
     * @throws Exception
     *             if the API call fails
     */
    @Test
    @Transactional
    public void ensureWorkout () throws Exception {
        service.deleteAll();

        final Workout w = new Workout( "Workout1" );
        w.addExercise( new Exercise( "Bench Press", "Chest, Shoulders, Triceps", 135.0, 4, 10 ) );

        mvc.perform( post( "/api/v1/workouts" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( w ) ) ).andExpect( status().isOk() );

    }
}
