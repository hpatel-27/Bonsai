// Profile.jsx
import React, { useEffect, useState } from "react";
import { userService } from "../services/userService";

const Profile = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const userData = await userService.getUserProfile(1); // Assume userId is 1
        setUser(userData);
      } catch (error) {
        console.error("Error fetching user profile", error);
      }
    };

    fetchUserProfile();
  }, []);

  if (!user) return <div>Loading...</div>;

  return (
    <div>
      <h1>{user.username}'s Profile</h1>
      <p>Email: {user.email}</p>
    </div>
  );
};

export default Profile;
