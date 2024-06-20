package hpatel.Bonsai.api;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
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
import hpatel.Bonsai.models.User;
import hpatel.Bonsai.services.UserService;

/**
 * Class to test the user API
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith ( SpringExtension.class )
public class APIUserTest {
    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    /**
     * The web application context
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * The user service
     */
    @Autowired
    private UserService           service;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();

        service.deleteAll();
    }

    /**
     * Tests the post user route.
     *
     * @throws Exception
     *             if the API call fails
     */
    @Test
    @Transactional
    public void testPostUser () throws Exception {
        service.deleteAll();

        final User u = new User();
        u.setUsername( "user" );
        u.setPassword( "pw" );
        u.setRole( "admin" );

        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( u ) ) ).andExpect( status().isOk() );

        Assertions.assertEquals( 1, (int) service.count() );

        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( u ) ) ).andExpect( status().isConflict() );
    }

    /**
     * Tests the delete user route.
     *
     * @throws Exception
     *             if the API call fails
     */
    @Test
    @Transactional
    public void testDeleteUser () throws Exception {
        service.deleteAll();

        final User u = new User();
        u.setUsername( "user1" );
        u.setPassword( "pw" );
        u.setRole( "admin" );

        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( u ) ) ).andExpect( status().isOk() );

        String user = mvc.perform( get( "/api/v1/users" ) ).andDo( print() ).andExpect( status().isOk() ).andReturn()
                .getResponse().getContentAsString();

        assertTrue( user.contains( "user1" ) );

        mvc.perform( delete( "/api/v1/users/user1" ).contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isOk() );

        user = mvc.perform( get( "/api/v1/users" ) ).andDo( print() ).andExpect( status().isOk() ).andReturn()
                .getResponse().getContentAsString();

        assertFalse( user.contains( "user1" ) );

        mvc.perform( delete( "/api/v1/users/user2" ).contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isNotFound() );
    }

    /**
     * Tests the get user by username route.
     *
     * @throws Exception
     *             if the API call fails
     */
    @Test
    @Transactional
    public void testGetUserByUsername () throws Exception {
        service.deleteAll();

        final User u = new User();
        u.setUsername( "user1" );
        u.setPassword( "pw" );
        u.setRole( "admin" );

        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( u ) ) ).andExpect( status().isOk() );

        final String user = mvc.perform( get( "/api/v1/users/user1" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        assertTrue( user.contains( "user1" ) );
        assertTrue( user.contains( "admin" ) );

        mvc.perform( get( "/api/v1/users/user2" ).contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isNotFound() );
    }

}
