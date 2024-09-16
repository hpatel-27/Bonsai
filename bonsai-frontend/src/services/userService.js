import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

const getUser = () => {
  return axios.get(`${API_URL}/user`);
};

export default {
  getUser,
};
