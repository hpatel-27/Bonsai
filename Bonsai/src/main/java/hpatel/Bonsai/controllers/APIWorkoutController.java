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

import hpatel.Bonsai.models.Workout;
import hpatel.Bonsai.services.WorkoutService;

/**
 * This is the controller that holds the REST endpoints that handle CRUD
 * operations for Workouts.
 *
 * Spring will automatically convert all of the ResponseEntity and List results
 * to JSON
 *
 * @author Harsh Patel
 *
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
@RestController
public class APIWorkoutController extends APIController {

    /**
     * WorkoutService object, to be autowired in by Spring to allow for
     * manipulating the Workout model
     */
    @Autowired
    private WorkoutService service;

    /**
     * REST API method to provide GET access to all workouts in the system
     *
     * @return JSON representation of all workouts
     */
    @GetMapping ( BASE_PATH + "/workouts" )
    public List<Workout> getWorkouts () {
        return service.findAll();
    }

    /**
     * REST API method to provide GET access to a specific workout, as indicated
     * by the path variable provided (the name of the workout desired)
     *
     * @param name
     *            workout name
     * @return response to the request
     */
    @GetMapping ( BASE_PATH + "/workouts/{name}" )
    public ResponseEntity getWorkout ( @PathVariable ( "name" ) final String name ) {
        final Workout workout = service.findByName( name );
        return null == workout
                ? new ResponseEntity( errorResponse( "No workout found with name " + name ), HttpStatus.NOT_FOUND )
                : new ResponseEntity( workout, HttpStatus.OK );
    }

    /**
     * REST API method to provide POST access to the workout model. This is used
     * to create a new workout by automatically converting the JSON RequestBody
     * provided to a workout object. Invalid JSON will fail.
     *
     * @param recipe
     *            The valid workout to be saved.
     * @return ResponseEntity indicating success if the Workout could be saved
     *         to the inventory, or an error if it could not be
     */
    @PostMapping ( BASE_PATH + "/workouts" )
    public ResponseEntity createWorkout ( @RequestBody final Workout workout ) {
        if ( null != service.findByName( workout.getName() ) ) {
            return new ResponseEntity(
                    errorResponse( "Workout with the name " + workout.getName() + " already exists" ),
                    HttpStatus.CONFLICT );
        }
        if ( service.findAll().size() < 3 ) {
            service.save( workout );
            return new ResponseEntity( successResponse( workout.getName() + " successfully created" ), HttpStatus.OK );
        }
        else {
            return new ResponseEntity(
                    errorResponse( "Insufficient space in workout book for workout " + workout.getName() ),
                    HttpStatus.INSUFFICIENT_STORAGE );
        }
    }

    /**
     * REST API method to provide PUT access to the Workout model. This is used
     * to edit and update a Workout already in the database.
     *
     * @param name
     *            The name of the Workout to update
     * @param newWorkout
     *            Workout matching the updated recipe
     * @return ResponseEntity indicating success if the workout was successfully
     *         updated or an error if it could not be
     *
     */
    @PutMapping ( BASE_PATH + "/workouts/{name}" )
    public ResponseEntity editWorkout ( @PathVariable final String name, @RequestBody final Workout newWorkout ) {
        // Get the current workout matching name
        final Workout currWorkout = service.findByName( name );

        // Return not found if the workout with the given name doesn't exist
        if ( null == currWorkout ) {
            return new ResponseEntity( errorResponse( "No workout found for name " + name ), HttpStatus.NOT_FOUND );
        }

        // Update the current workout to match the new one
        currWorkout.updateWorkout( newWorkout );
        service.save( currWorkout );

        // Return a success response
        return new ResponseEntity( successResponse( currWorkout.getName() + " successfully updated" ), HttpStatus.OK );
    }

    /**
     * REST API method to allow deleting a Workout from the Bonsai's database ,
     * by making a DELETE request to the API endpoint and indicating the workout
     * to delete (as a path variable)
     *
     * @param name
     *            The name of the Workout to delete
     * @return Success if the workout could be deleted; an error if the Workout
     *         does not exist
     */
    @DeleteMapping ( BASE_PATH + "/workouts/{name}" )
    public ResponseEntity deleteWorkout ( @PathVariable final String name ) {
        final Workout workout = service.findByName( name );
        if ( null == workout ) {
            return new ResponseEntity( errorResponse( "No workout found for name " + name ), HttpStatus.NOT_FOUND );
        }
        service.delete( workout );

        return new ResponseEntity( successResponse( name + " was deleted successfully" ), HttpStatus.OK );
    }
}
