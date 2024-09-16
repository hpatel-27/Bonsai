import React, { useEffect, useState } from "react";
import workoutService from "../services/workoutService";

const Workout = () => {
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    workoutService
      .getWorkouts()
      .then((response) => {
        setWorkouts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching workouts", error);
      });
  }, []);

  return (
    <div>
      <h2>Workouts</h2>
      {workouts.map((workout) => (
        <div key={workout.id}>
          <h3>{workout.name}</h3>
          <ul>
            {workout.exercises.map((exercise) => (
              <li key={exercise.id}>{exercise.name}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default Workout;
