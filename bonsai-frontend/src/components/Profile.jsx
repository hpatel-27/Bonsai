import React, { useEffect, useState } from "react";
import userService from "../services/userService";

const Profile = () => {
  const [user, setUser] = useState({});

  useEffect(() => {
    userService
      .getUser()
      .then((response) => {
        setUser(response.data);
      })
      .catch((error) => {
        console.error("Error fetching user profile", error);
      });
  }, []);

  return (
    <div>
      <h2>User Profile</h2>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
    </div>
  );
};

export default Profile;
