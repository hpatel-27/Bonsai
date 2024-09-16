import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

const getUserWeights = () => {
  return axios.get(`${API_URL}/weights`);
};

export default {
  getUserWeights,
};
