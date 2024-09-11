package hpatel.Bonsai.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import hpatel.Bonsai.services.ExerciseService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith ( SpringExtension.class )
public class APIExerciseTest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    /**
     * Context for the web application
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * The exercise service object
     */
    @Autowired
    private ExerciseService       service;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();

        service.deleteAll();
    }

    /**
     * Tests that the exercises are correctly received as a list of exercises.
     *
     * @throws Exception
     *             if the post or get fails
     */
    @Test
    @Transactional
    public void testGetExercises () throws Exception {

        final Exercise e1 = new Exercise( "Bench Press", "Chest, Triceps, Shoulders", 135.0, 4, 14 );
        final Exercise e2 = new Exercise( "Low Chest Press", "Chest, Triceps", 180.0, 3, 10 );
        final Exercise e3 = new Exercise( "Tricep Extensions", "Triceps", 22.5, 3, 15 );
        final Exercise e4 = new Exercise( "Shoulder Press", "Shoulders", 150.0, 4, 8 );

        mvc.perform( post( "/api/v1/exercises" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( e1 ) ) ).andExpect( status().isOk() );
        mvc.perform( post( "/api/v1/exercises" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( e2 ) ) ).andExpect( status().isOk() );
        mvc.perform( post( "/api/v1/exercises" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( e3 ) ) ).andExpect( status().isOk() );
        mvc.perform( post( "/api/v1/exercises" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( e4 ) ) ).andExpect( status().isOk() );
        assertEquals( 4, (int) service.count() );

        final String exerciseString = mvc.perform( get( "/api/v1/exercises" ) ).andDo( print() )
                .andExpect( status().isOk() ).andReturn().getResponse().getContentAsString();

        // assertEquals( "", exerciseString );
        assertTrue( exerciseString.contains(
                "\"name\":\"Bench Press\",\"description\":\"Chest, Triceps, Shoulders\",\"weight\":135.0,\"sets\":4,\"reps\":14" ) );
        assertTrue( exerciseString.contains(
                "\"name\":\"Low Chest Press\",\"description\":\"Chest, Triceps\",\"weight\":180.0,\"sets\":3,\"reps\":10" ) );
        assertTrue( exerciseString.contains(
                "\"name\":\"Tricep Extensions\",\"description\":\"Triceps\",\"weight\":22.5,\"sets\":3,\"reps\":15" ) );
        assertTrue( exerciseString.contains(
                "\"name\":\"Shoulder Press\",\"description\":\"Shoulders\",\"weight\":150.0,\"sets\":4,\"reps\":8" ) );

        service.deleteAll();

    }
}
