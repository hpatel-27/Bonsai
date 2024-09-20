// workoutService.js
import { request } from "../utils/request";

const BASE_URL = "/api/v1/workouts";

export const workoutService = {
  getWorkouts: async () => {
    return request(`${BASE_URL}`, "GET");
  },

  getWorkout: async (workoutId) => {
    return request(`${BASE_URL}/${workoutId}`, "GET");
  },

  createWorkout: async (workoutData) => {
    return request(`${BASE_URL}/create`, "POST", workoutData);
  },

  updateWorkout: async (workoutId, workoutData) => {
    return request(`${BASE_URL}/update/${workoutId}`, "PUT", workoutData);
  },

  deleteWorkout: async (workoutId) => {
    return request(`${BASE_URL}/delete/${workoutId}`, "DELETE");
  },
};
