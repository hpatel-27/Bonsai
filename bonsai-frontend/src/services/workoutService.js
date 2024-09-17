import axios from "axios";

// const API_URL = "http://localhost:8080/api/v1";

const getWorkouts = () => {
  return axios.get(`http://localhost:8080/api/v1/workouts`);
};

export default {
  getWorkouts,
};
