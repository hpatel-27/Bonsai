import axios from "axios";

// const API_URL = "http://localhost:8080/api/v1";

const getUserWeights = () => {
  return axios.get(`http://localhost:8080/api/v1/weights`);
};

export default {
  getUserWeights,
};
