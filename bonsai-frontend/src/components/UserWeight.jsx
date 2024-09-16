import React, { useEffect, useState } from "react";
import userWeightService from "../services/userWeightService";

const UserWeight = () => {
  const [weights, setWeights] = useState([]);

  useEffect(() => {
    userWeightService
      .getUserWeights()
      .then((response) => {
        setWeights(response.data);
      })
      .catch((error) => {
        console.error("Error fetching user weights", error);
      });
  }, []);

  return (
    <div>
      <h2>User Weight</h2>
      <ul>
        {weights.map((weight) => (
          <li key={weight.id}>
            {weight.value}lbs - {weight.date}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default UserWeight;
