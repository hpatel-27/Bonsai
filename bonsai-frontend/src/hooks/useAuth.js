// useAuth.js
import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";

const useAuth = () => {
  const [authenticated, setAuthenticated] = useState(false);
  const history = useHistory();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      history.push("/login");
    } else {
      setAuthenticated(true);
    }
  }, [history]);

  return authenticated;
};

export default useAuth;
