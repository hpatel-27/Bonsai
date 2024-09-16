import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

const getWorkouts = () => {
  return axios.get(`${API_URL}/workouts`);
};

export default {
  getWorkouts,
};
