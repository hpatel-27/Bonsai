// src/components/Workout.jsx
import React, { useEffect, useState } from "react";
import { workoutService } from "../services/workoutService";

const Workout = () => {
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    const fetchWorkouts = async () => {
      try {
        const data = await workoutService.getWorkouts();
        setWorkouts(data);
      } catch (error) {
        console.error("Error fetching workouts", error);
      }
    };

    fetchWorkouts();
  }, []);

  return (
    <div>
      <h2>Workout List</h2>
      <ul>
        {workouts.map((workout) => (
          <li key={workout.id}>{workout.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default Workout;
