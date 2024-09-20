// userService.js
import { request } from "../utils/request";

const BASE_URL = "/api/v1/users";

export const userService = {
  login: async (username, password) => {
    const body = { username, password };
    return request(`${BASE_URL}/login`, "POST", body);
  },

  register: async (userData) => {
    return request(`${BASE_URL}/register`, "POST", userData);
  },

  getUserProfile: async (userId) => {
    return request(`${BASE_URL}/${userId}`, "GET");
  },
};
