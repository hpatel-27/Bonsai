// UserWeight.jsx
import React, { useEffect, useState } from "react";
import { userWeightService } from "../services/userWeightService";

const UserWeight = () => {
  const [weights, setWeights] = useState([]);
  const [newWeight, setNewWeight] = useState("");

  useEffect(() => {
    const fetchUserWeights = async () => {
      try {
        const data = await userWeightService.getUserWeights();
        setWeights(data);
      } catch (error) {
        console.error("Error fetching user weights", error);
      }
    };

    fetchUserWeights();
  }, []);

  const handleAddWeight = async () => {
    if (newWeight) {
      try {
        await userWeightService.addUserWeight({ weight: newWeight });
        setWeights([...weights, { weight: newWeight }]);
        setNewWeight("");
      } catch (error) {
        console.error("Error adding weight", error);
      }
    }
  };

  return (
    <div>
      <h1>User Weights</h1>
      <ul>
        {weights.map((weight, index) => (
          <li key={index}>{weight.weight} kg</li>
        ))}
      </ul>
      <input
        type="number"
        value={newWeight}
        onChange={(e) => setNewWeight(e.target.value)}
        placeholder="Enter new weight"
      />
      <button onClick={handleAddWeight}>Add Weight</button>
    </div>
  );
};

export default UserWeight;
