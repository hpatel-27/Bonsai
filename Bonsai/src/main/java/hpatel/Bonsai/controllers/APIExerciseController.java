package hpatel.Bonsai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hpatel.Bonsai.models.Exercise;
import hpatel.Bonsai.services.ExerciseService;

/**
 * This is the controller that holds the REST endpoints that handle getting,
 * creating, and deleting Exercises.
 *
 * Spring will automatically convert all of the ResponseEntity and List results
 * to JSON
 *
 * @author Harsh Patel
 *
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
@RestController
public class APIExerciseController extends APIController {

    /**
     * ExerciseService object, to be autowired in by Spring to allow for
     * manipulating the Exercise model
     */
    @Autowired
    private ExerciseService service;

    /**
     * REST API method to provide GET access to all exercises in the system
     *
     * @return JSON representation of all exercises
     */
    @GetMapping ( BASE_PATH + "/exercises" )
    public List<Exercise> getExercises () {
        return service.findAll();
    }

    /**
     * REST API method to provide GET access to a specific exercise
     *
     * @param name
     *            Name of the exercise to get
     * @return JSON representation of all exercises
     */
    @GetMapping ( BASE_PATH + "exercises/{name}" )
    public ResponseEntity getExercise ( @PathVariable ( "name" ) final String name ) {

        final Exercise exer = service.findByName( name );

        return null == exer
                ? new ResponseEntity( errorResponse( "No exercise found with name " + name ), HttpStatus.NOT_FOUND )
                : new ResponseEntity( exer, HttpStatus.OK );
    }

    /**
     * REST API method to provide POST access to the Exercise model. This is
     * used to create a new Exercise by automatically converting the JSON
     * RequestBody provided to a Exercise object. Invalid JSON will fail.
     *
     * @param exer
     *            The valid Exercise to be saved.
     * @return ResponseEntity indicating success if the Exercise could be saved
     *         to the database, or an error if it could not be
     */
    @PostMapping ( BASE_PATH + "/exercises" )
    public ResponseEntity createExercise ( @RequestBody final Exercise exer ) {
        if ( null != service.findByName( exer.getName() ) ) {
            return new ResponseEntity(
                    errorResponse( "Exercise with the name " + exer.getName().toString() + " already exists" ),
                    HttpStatus.CONFLICT );
        }
        service.save( exer );
        return new ResponseEntity( successResponse( exer.getName().toString() + " successfully created" ),
                HttpStatus.OK );

    }

    /**
     * REST API method to allow deleting an Exercise from the Bonsai database,
     * by making a DELETE request to the API endpoint and indicating the
     * Exercise to delete (as a path variable)
     *
     * @param name
     *            The name of the Exercise to delete
     * @return Success if the Exercise could be deleted; an error if the
     *         Exercise does not exist
     */
    @DeleteMapping ( BASE_PATH + "/exercises/{name}" )
    public ResponseEntity deleteExercise ( @PathVariable final String name ) {

        final Exercise exer = service.findByName( name );
        if ( null == exer ) {
            return new ResponseEntity( errorResponse( "No Exercise found for name " + name ), HttpStatus.NOT_FOUND );
        }
        service.delete( exer );

        return new ResponseEntity( successResponse( name + " was deleted successfully" ), HttpStatus.OK );
    }

    /**
     * REST API method to provide PUT access to the Exercise model. This is used
     * to edit an existing Exercise by automatically converting the JSON
     * RequestBody provided to a Exercise object. Invalid JSON will fail.
     *
     * @param name
     *            the name of the exercise
     * @param exercise
     *            the exercise to be saved
     * @return ResponseEntity indicating success if the Exercise could be saved
     *         to the database, or an error if it could not be
     */
    @PutMapping ( BASE_PATH + "/exercises/{name}" )
    public ResponseEntity editExercise ( @PathVariable ( "name" ) final String name,
            @RequestBody final Exercise exercise ) {

        if ( null == service.findByName( name ) ) {
            return new ResponseEntity(
                    errorResponse( "Exercise with the name " + exercise.getName().toString() + " does not exist" ),
                    HttpStatus.CONFLICT );
        }
        if ( !name.equals( exercise.getName().toString() ) ) {
            return new ResponseEntity(
                    errorResponse( "Exercise with the name " + name + " does not match object provided" ),
                    HttpStatus.CONFLICT );
        }
        final Exercise exer = service.findByName( name );
        exer.setDescription( exercise.getDescription() );
        service.save( exer );
        return new ResponseEntity( successResponse( exercise.getName().toString() + " successfully created" ),
                HttpStatus.OK );

    }

}
