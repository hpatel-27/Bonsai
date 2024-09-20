// src/components/Home.jsx
import React from "react";
import Workout from "./Workout";

const Home = () => {
  return (
    <div>
      <h1>Welcome to the Home Page</h1>
      <p>
        This is your dashboard where you can see a summary of your activities.
      </p>

      {/* Include the Workout component here */}
      <Workout />
    </div>
  );
};

export default Home;
