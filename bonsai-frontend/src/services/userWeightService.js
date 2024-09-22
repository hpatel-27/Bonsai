// userWeightService.js
import { request } from "../utils/request";

const BASE_URL = "/api/v1/userweight";

export const userWeightService = {
  getUserWeights: async () => {
    return request(`${BASE_URL}`, "GET");
  },

  addUserWeight: async (weightData) => {
    return request(`${BASE_URL}/add`, "POST", weightData);
  },
};
