import axios from "axios";

// const API_URL = "http://localhost:8080/api/v1";

const getUser = () => {
  return axios.get(`http://localhost:8080/api/v1/user`);
};

export default {
  getUser,
};
