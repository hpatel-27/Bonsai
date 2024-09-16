import React from "react";
import Profile from "./Profile";
import Workout from "./Workout";
import UserWeight from "./UserWeight";

const Home = () => {
  return (
    <div>
      <h1>Welcome to the Fitness App Dashboard</h1>
      <Profile />
      <Workout />
      <UserWeight />
    </div>
  );
};

export default Home;
